package com.example.demo.service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private  ProductRepository  productRepository;
    private  ProductMapper procutMapper;

    @Autowired
    public  ProductServiceImpl( ProductRepository productRepository, ProductMapper procutMapper){
        this.productRepository = productRepository;
        this.productMapper = procutMapper
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto){
        product = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDto update(Long id,ProductDto productDto){
        product = productMapper.toEntity(productDto);
        product.setId(id);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto findById(long id){
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("product not found"));

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
        Product product = findById(id); 
        productRepository.delete(product);
    }
}