package com.example.ecommerce.service.implementation;

import com.example.ecommerce.dto.requestDto.CartRequest;
import com.example.ecommerce.dto.responseDto.CartResponse;
import com.example.ecommerce.dto.responseDto.ItemResponse;
import com.example.ecommerce.dto.responseDto.OrderResponse;
import com.example.ecommerce.exception.CustomerNotExistException;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.InvalidCardException;
import com.example.ecommerce.exception.InvalidItemIdException;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.CardRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.OrderedRepository;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.transformer.CartTransformer;
import com.example.ecommerce.transformer.ItemTransformer;
import com.example.ecommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;


    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    OrderService orderService;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CartResponse saveToCart(Integer customerId, Item item)
    {
        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        Integer newTotal = cart.getCartTotal()+(item.getRequiredQuantity()*item.getProduct().getPrice());

        cart.setCartTotal(newTotal);
        cart.getItemList().add(item);
        cart.setNumberOfItems(cart.getItemList().size());

        item.setCart(cart);

        Cart savedCart = cartRepository.save(cart);

        CartResponse cartResponse = CartTransformer.cartToCartResponse(savedCart);

        List<ItemResponse> items = new ArrayList<>();

        for(Item savedItem : cart.getItemList())
        {
            ItemResponse itemResponse = ItemTransformer.itemToItemResponse(savedItem);
            items.add(itemResponse);
        }

        cartResponse.setItems(items);
        return cartResponse;
    }

    public void resetCart(Cart cart)
    {
        cart.setNumberOfItems(0);
        cart.setCartTotal(0);
        for(Item item : cart.getItemList())
        {
            item.setCart(null);
        }
        cart.getItemList().clear();
    }

    @Override
    public OrderResponse checkOutCart(CartRequest cartRequest) throws Exception {

        //1
        Customer customer;
        try {
             customer = customerRepository.findById(cartRequest.getCustomerId()).get();

        }catch(Exception e)
        {
            throw new CustomerNotExistException("Customer not found");
        }

        //2
       Card card =  cardRepository.findByCardNo(cartRequest.getCardNo());

        if( (card==null) || (card.getCvv()!=cartRequest.getCvv()) || (card.getCustomer()!=customer))
        {
            throw new InvalidCardException("Your Card is not valid");
        }

        //3
        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0)
            throw new Exception("Cart is empty, add items");

        try{
            Ordered ordered = orderService.placeOrder(customer,card);
            customer.getOrderedList().add(ordered);
            resetCart(cart);

            Ordered saveOrder = orderedRepository.save(ordered);

            OrderResponse orderResponse = OrderTransformer.orderToOrderResponse(saveOrder);

            List<ItemResponse> items = new ArrayList<>();

            for(Item savedItem : saveOrder.getItems())
            {
                ItemResponse itemResponse = ItemTransformer.itemToItemResponse(savedItem);
                items.add(itemResponse);
            }

            orderResponse.setItems(items);
            return orderResponse;

        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }

    }


    @Override
    public String removeItemFromCart(Integer cartId, Integer itemId) throws InvalidCardException, InvalidItemIdException {
        Cart cart = cartRepository.findById(cartId).get();
         if(cart.equals(null)) throw new InvalidCardException("Cart not found");

        Item itemToRemove = null;
        for (Item item : cart.getItemList()) {
            if (item.getId().equals(itemId)) {
                itemToRemove = item;
                break;
            }
        }

        if(itemToRemove==null) throw new InvalidItemIdException("Invalid Item ID");


        if (itemToRemove != null) {
            cart.getItemList().remove(itemToRemove);
            cart.setNumberOfItems(cart.getItemList().size());
            cart.setCartTotal(calculateCartTotal(cart));
            itemToRemove.setCart(null);
            cartRepository.save(cart);
        }
        return "Item removed from cart";
    }
    @Override
    public List<ItemResponse> getAllItemsInCart(Integer customerId) throws InvalidCardException, CustomerNotExistException {

        Customer customer = customerRepository.findById(customerId).get();
        if(customer==null) throw new CustomerNotExistException("Customer Not Found");
        Integer cartId = customer.getCart().getId();
        Cart cart = cartRepository.findById(cartId).get();
        if(cart.equals(null)) throw new InvalidCardException("Cart not found");

        List<ItemResponse> itemResponseList = new ArrayList<>();
        for (Item item : cart.getItemList()) {
            ItemResponse itemResponse = ItemTransformer.itemToItemResponse(item);
            itemResponseList.add(itemResponse);
        }

        return itemResponseList;
    }
//    @Override
//    public void sendCartEmail(Integer cartId) {
//        // Logic to send email for the cart
//        // You can implement the email sending functionality here
//    }



    @Override
    public int calculateCartTotal(Cart cart) {
        int total = 0;
        for (Item item : cart.getItemList()) {
            total += item.getRequiredQuantity() * item.getProduct().getPrice();
        }
        return total;
    }

}
