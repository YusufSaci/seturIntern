package com.example.demo.dto;

import java.util.List;

public record CustomerDto(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        List<OrderDto> orders
) {}