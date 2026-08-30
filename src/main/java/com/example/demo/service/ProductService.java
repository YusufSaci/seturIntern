package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService{

    Product save(Product product);

    Product findById(long id);

    List<Product> findAll();

    void deleteById(long id);

   
}