package com.example.demo.service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private  ProductRepository  productRepository;
    private ProductMapper productMapper;
    private CategoryRepository categoryRepository;

    @Autowired
    public  ProductServiceImpl( ProductRepository productRepository, ProductMapper procutMapper,
                                CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.productMapper = procutMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto){
        Category category = categoryRepository.findById(productDto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("category not found"));;

        Product product = productMapper.toEntity(productDto,category);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDto update(Long id,ProductDto productDto){

        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));

        Category category = categoryRepository.findById(productDto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("category not found"));

        Product product = productMapper.toEntity(productDto,category);

        product.setId(id);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto findById(long id){

        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("product not found"));

        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> findAll(){

        List<ProductDto> products =  productRepository.findAll().stream()
                        .map(product -> productMapper.toDto(product)).toList();
        return  products;
    }

    @Override
    @Transactional
    public  void deleteById(long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));

        productRepository.delete(product);
    }
}