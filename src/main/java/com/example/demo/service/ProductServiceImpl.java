package com.example.demo.service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public ProductDto save(ProductDto dto) {

        Category category = getCategoryOrThrow(dto.categoryId());

        Product product = productMapper.toEntity(dto, category);

        return productMapper.toDto(productRepository.save(product));
    }

    @Transactional
    @Override
    public ProductDto update(Long id, ProductDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));

        Category category = getCategoryOrThrow(dto.categoryId());

        product.setProductName(dto.productName());
        product.setPrice(dto.price());
        product.setCategory(category);

        return productMapper.toDto(product);
    }

    @Override
    public ProductDto findById(long id) {

        return productMapper.toDto(productRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException("product not found"))
        );
    }

    @Override
    public List<ProductDto> findAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public void deleteById(long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));

        productRepository.delete(product);
    }

    private Category getCategoryOrThrow(
            Long categoryId) {

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("category not found"));
    }
}