package com.example.demo.dto;

public record OrderDto(
        Long id,
        Integer amount,
        Long customerId,
        String customerName,
        String productName,
        Long productId,
        Integer productPrice
) {
    public OrderDto(int amount, Long productId) {
        this(null, amount, null, null, null, productId, null);
    }
}