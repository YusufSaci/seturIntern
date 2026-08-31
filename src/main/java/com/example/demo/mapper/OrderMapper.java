package com.example.demo.mapper;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getAmount(),
                order.getCustomer().getId(),
                order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName(),
                order.getProduct().getProductName(),
                order.getProduct().getId(),
                order.getProduct().getPrice()
        );
    }

    public Order toEntity(OrderDto dto, Customer customer, Product product) {

        Order order = new Order(product, dto.amount());
        order.setId(dto.id());
        return order;
    }
}