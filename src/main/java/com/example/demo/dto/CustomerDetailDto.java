package com.example.demo.dto;

import java.util.List;

public record CustomerDetailDto(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        List<OrderDto> orders
) {}
