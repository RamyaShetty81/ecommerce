package com.example.ecommerce.exception;

public class ProductOutOfStock extends Exception{
    public ProductOutOfStock(String message) {
        super(message);
    }
}
