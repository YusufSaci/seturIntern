package com.example.demo.dto;

public record ProductDto(
        Long id,
        String productName,
        Integer price,
        Long categoryId,
        String categoryName
){}