package com.example.demo.exception.dto;

public record ErrorResponseDto(
        int status,
        String message,
        Long timeStamp

){ }
