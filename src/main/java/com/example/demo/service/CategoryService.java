package com.example.demo.service;

import com.example.demo.dto.CategoryDetailDto;
import com.example.demo.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto);

    CategoryDto update(CategoryDto dto, Long id);

    CategoryDto findById(Long id);

    CategoryDetailDto findDetailById(Long id);

    List<CategoryDto> findAll();

    void deleteById(Long id);
}