package com.example.demo.service;

import com.example.demo.dto.CategoryDto;

import java.util.List;

public interface CategoryService{

    CategoryDto save(CategoryDto category);

    CategoryDto findById(long id);

    List<CategoryDto> findAll();

    void deleteById(long id);

    CategoryDto update(CategoryDto categoryDto, Long id);

    
}