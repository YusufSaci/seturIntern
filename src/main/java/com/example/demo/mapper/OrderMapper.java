package com.example.demo.mapper;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(
            expression = "java(order.getCustomer().getFirstName() + \" \" + order.getCustomer().getLastName())",
            target = "customerName"
    )
    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.price", target = "productPrice")
    OrderDto toDto(Order order);

    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "id", source = "dto.id")
    Order toEntity(OrderDto dto, Customer customer, Product product);
}