package com.example.demo.service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dto.CategoryDetailDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.mapper.CategoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public CategoryDto save(CategoryDto dto) {

        Category category = categoryMapper.toEntity(dto);

        Category saved = categoryRepository.save(category);

        return categoryMapper.toDto(saved);
    }

    @Transactional
    @Override
    public CategoryDto update(CategoryDto dto, Long id) {

        Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new CategoryNotFoundException("category not found"));

        category.setCategoryName(dto.categoryName());

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDetailDto findDetailById(Long id) {

        Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new CategoryNotFoundException("category not found"));

        return categoryMapper.toDetailDto(category);
    }

    @Override
    public CategoryDto findById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("category not found"));

        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> findAll() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("category not found"));

        category.getProducts().forEach(product -> product.setCategory(null));

        categoryRepository.delete(category);
    }
}