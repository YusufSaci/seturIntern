package com.example.demo.dto;

import java.util.List;

public record CategoryDto(
        Long id,
        String categoryName,
        List<ProductDto> products
) {}