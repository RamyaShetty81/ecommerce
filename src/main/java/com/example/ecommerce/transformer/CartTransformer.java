package com.example.ecommerce.transformer;

import com.example.ecommerce.dto.requestDto.CartRequest;
import com.example.ecommerce.dto.responseDto.CartResponse;
import com.example.ecommerce.model.Cart;

public class CartTransformer {


    public static CartResponse cartToCartResponse(Cart cart)
    {
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .noOfItems(cart.getNumberOfItems())
                .build();
    }
}
