package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService{

    Category save(Category category);

    Category findById(long id);

    List<Category> findAll();

    void deleteById(long id);

    
}