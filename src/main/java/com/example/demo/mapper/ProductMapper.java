package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper{


    public ProductDto toDto(Product product){
        return new ProductDto(
            product.getId(),
            product.getProductName(),
            product.getPrice(),
            product.getCategory().getId(),
            product.getCategory().getCategoryName()

        );
    }

    public Product toEntity(ProductDto productDto, Category category){
        Product product = new Product(productDto.productName(), productDto.price());
        product.setCategory(category);
        return product;

    }



}