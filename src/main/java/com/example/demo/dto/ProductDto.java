package com.example.demo.dto;

public record ProductDto(
        Long id,
        String productName,
        int price,
        Long categoryId,
        String categoryName
){}