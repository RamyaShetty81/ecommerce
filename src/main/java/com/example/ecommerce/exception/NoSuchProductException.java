package com.example.ecommerce.exception;

public class NoSuchProductException extends Exception{
    public NoSuchProductException(String message) {
        super(message);
    }
}
