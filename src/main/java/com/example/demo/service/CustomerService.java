package com.example.demo.service;

import com.example.demo.dto.CustomerDetailDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerDto dto);

    CustomerDto findById(Long id);

    CustomerDetailDto findDetailById(Long id);

    Customer findEntityById(Long id);

    List<CustomerDto> findAll();

    CustomerDto update(CustomerDto dto, Long id);

    void deleteById(Long id);
}