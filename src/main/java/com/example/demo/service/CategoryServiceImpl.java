package com.example.demo.service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public CategoryDto save(CategoryDto categoryDto){

        Category category = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryDto update(CategoryDto categoryDto, Long id){

        Category category = categoryMapper.toEntity(categoryDto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto findById(long id){
        Category category =categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("category not found"));

        
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> findAll(){
        List<CategoryDto> categories =  categoryRepository.findAll().stream()
                        .map(category -> categoryMapper.toDto(category)).toList();
        return categories;
    }

    @Override
    @Transactional
    public  void deleteById(long id){
        Category category = findById(id);

        List<Product> products = category.getProducts();

        for(Product tempProduct : products){
            tempProduct.setCategory(null);
        }
        
        categoryRepository.delete(category);
    }
}