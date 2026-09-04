package com.example.demo.exception;

public class ProductNotFoundException extends NotFoundException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
