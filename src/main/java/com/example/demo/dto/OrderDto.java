package com.example.demo.dto;

public record OrderDto(
        Long id,
        Integer amount,
        Long customerId,
        String customerName,
        Long productId,
        String productName,
        Integer productPrice
) {}