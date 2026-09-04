package com.example.demo.exception;

public class OrderNotFoundException extends NotFoundException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
