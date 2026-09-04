package com.example.demo.exception;

public class CustomerNotFoundException extends NotFoundException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
