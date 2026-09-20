package com.example.demo.dto;


public record CustomerDto(
        Long id,
        String firstName,
        String lastName,
        Integer age
) {}