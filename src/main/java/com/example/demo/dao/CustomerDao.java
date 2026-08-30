package com.example.demo.dao;


import com.example.demo.entity.Customer;

import java.util.List;

public interface CustomerDao{

    void save(Customer customer);

    Customer findById(long id);

    List<Customer> findAll();

    void deleteById(long id);

    Customer update(Customer customer);

}