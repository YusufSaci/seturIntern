package com.example.demo.dto;

import java.util.List;

public record CategoryDetailDto(
        Long id,
        String categoryName,
        List<ProductDto> products
) {}
