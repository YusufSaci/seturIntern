package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;

import java.util.List;

public interface OrderService{

    OrderDto save(Long customerId,OrderDto order);

    OrderDto findById(long id);

    List<OrderDto> findAll();

    void deleteById(long id);

}