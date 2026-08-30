package com.example.demo.mapper;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    private final OrderMapper orderMapper;

    public CustomerMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public CustomerDto toDto(Customer customer) {
        List<OrderDto> orders = customer.getOrders().stream()
                .map(orderMapper::toDto)
                .toList();

        return new CustomerDto(customer.getId(), customer.getFirstName(),
                customer.getLastName(), customer.getAge(), orders);
    }

    public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer(dto.firstName(), dto.lastName(), dto.age());
        customer.setId(dto.id());
        return customer;
    }
}