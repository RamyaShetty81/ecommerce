package com.example.ecommerce.service.implementation;

import com.example.ecommerce.dto.requestDto.OrderRequest;
import com.example.ecommerce.dto.responseDto.ItemResponse;
import com.example.ecommerce.dto.responseDto.OrderResponse;
import com.example.ecommerce.exception.*;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.CardRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.OrderedRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.transformer.ItemTransformer;
import com.example.ecommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class OrderedServiceImplementation implements OrderService  {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    private OrderedRepository orderedRepository;

    @Override
    public Ordered placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getItemList()){
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            } catch (Exception e) {
                throw new ProductOutOfStock("Product Out of stock");
            }
        }
        order.setItems(orderedItems);
        for(Item item: orderedItems)
            item.setOrdered(order);
        order.setTotalValue(cart.getCartTotal());
        return order;
    }

//direct order
    @Override
    public OrderResponse placeDirectOrder(OrderRequest orderRequest) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequest.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequest.getProductId()).get();
        }
        catch(Exception e){
            throw new NoSuchProductException("Product doesn't exist");
        }

        Card card = cardRepository.findByCardNo(orderRequest.getCardNo());
        if(card==null || card.getCvv()!=orderRequest.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequest.getRequiredQuantity())
                .product(product)
                .build();
        try{
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItems().add(item);

        customer.getOrderedList().add(order);
        item.setOrdered(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order); // order and item

        OrderResponse orderResponse = OrderTransformer.orderToOrderResponse(savedOrder);

        List<ItemResponse> items = new ArrayList<>();
        for(Item itemEntity: savedOrder.getItems()){
            ItemResponse itemResponse = ItemTransformer.itemToItemResponse(itemEntity);
            items.add(itemResponse);
        }

        orderResponse.setItems(items);
        return orderResponse;

    }

    public String generateMaskedCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0;i<cardNo.length()-4;i++)
            maskedCardNo += 'X';
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;

    }


    @Override
    public List<OrderResponse> getOrdersForCustomer(Integer customerId) throws CustomerNotExistException {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Customer Id is invalid !!");
        }
        List<Ordered> orders = orderedRepository.findByCustomer(customer);

        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Ordered order : orders) {
            orderResponses.add(OrderTransformer.orderToOrderResponse(order));
        }

        return orderResponses;

    }

    @Override
    public List<OrderResponse> getRecentOrders() {
        List<Ordered> orders = orderedRepository.findTop5ByOrderByOrderDateDesc();

        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Ordered order : orders) {
            orderResponses.add(OrderTransformer.orderToOrderResponse(order));
        }

        return orderResponses;
    }

    @Override
    public String deleteOrder(Integer orderId) throws IdNotPresentException {
        try {
            orderedRepository.deleteById((orderId));
            return "Order deleted successfully";
        }
        catch (Exception e)
        {
            throw new IdNotPresentException("Invalid orderId");
        }
    }

    @Override
    public OrderResponse getOrderWithHighestTotal() {
        Ordered order = orderedRepository.findFirstByOrderByTotalValueDesc();
        Customer customer = order.getCustomer();

        OrderResponse orderResponse = OrderTransformer.orderToOrderResponse(order);
        orderResponse.setCustomerName(customer.getName());

        return orderResponse;
    }

}