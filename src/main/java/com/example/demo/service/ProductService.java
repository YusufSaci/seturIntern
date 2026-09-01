package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService{

    ProductDto save(ProductDto product);

    ProductDto findById(long id);

    List<ProductDto> findAll();

    void deleteById(long id);

   
}