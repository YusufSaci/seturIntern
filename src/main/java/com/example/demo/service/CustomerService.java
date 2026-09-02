package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;

import java.util.List;

public interface CustomerService{

    void save(CustomerDto customer);

    CustomerDto findById(long id);

    Customer findEntityById(long id);

    List<CustomerDto> findAll();

    void deleteById(long id);

    CustomerDto update(CustomerDto customer,Long id);
    
}