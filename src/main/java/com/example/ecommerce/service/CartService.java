package com.example.ecommerce.service;

import com.example.ecommerce.dto.requestDto.CartRequest;
import com.example.ecommerce.dto.responseDto.CartResponse;
import com.example.ecommerce.dto.responseDto.ItemResponse;
import com.example.ecommerce.dto.responseDto.OrderResponse;
import com.example.ecommerce.exception.CustomerNotExistException;
import com.example.ecommerce.exception.InvalidCardException;
import com.example.ecommerce.exception.InvalidItemIdException;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Item;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface CartService {

    public CartResponse saveToCart(Integer customerId, Item item);

    public OrderResponse checkOutCart(CartRequest cartRequest) throws Exception;

    public String removeItemFromCart(Integer cartId, Integer itemId) throws InvalidCardException, InvalidItemIdException;

    public List<ItemResponse> getAllItemsInCart(Integer cartId) throws InvalidCardException, CustomerNotExistException;

    public int calculateCartTotal(Cart cart);

}
