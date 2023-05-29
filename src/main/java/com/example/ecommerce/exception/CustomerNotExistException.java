package com.example.ecommerce.exception;

public class CustomerNotExistException extends Exception{
    public CustomerNotExistException(String message) {
        super(message);
    }
}
