package com.example.ecommerce.service.implementation;

import com.example.ecommerce.Enum.Status;
import com.example.ecommerce.dto.requestDto.ItemRequest;
import com.example.ecommerce.exception.CustomerNotExistException;
import com.example.ecommerce.exception.NoSuchProductException;
import com.example.ecommerce.exception.ProductOutOfStock;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ItemRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ItemService;
import com.example.ecommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImplementation implements ItemService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item addItem(ItemRequest itemRequest) throws CustomerNotExistException, NoSuchProductException, ProductOutOfStock {
        Customer customer;
        try{
            customer = customerRepository.findById(itemRequest.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerNotExistException("Customer Id is invalid");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequest.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new NoSuchProductException("Customer Id is invalid");
        }

        if(itemRequest.getRequiredQuantity()> product.getQuantity() || product.getStatus()!= Status.AVAILABLE)
        {
            throw new ProductOutOfStock("Product out of stock");
        }

        Item item = ItemTransformer.itemRequestToItem(itemRequest);
        item.setProduct(product);

        product.getItemList().add(item);
        return itemRepository.save(item);

    }
}
