package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper{


    public ProductDto toDto(Product product){

        Long categoryId = null;
        String categoryName = null;
        if(product.getCategory() != null){
            categoryId =  product.getCategory().getId();
            categoryName = product.getCategory().getCategoryName();
        }
        return new ProductDto(
            product.getId(),
            product.getProductName(),
            product.getPrice(), 
            categoryId,
            categoryName

        );
    }

    public Product toEntity(ProductDto productDto, Category category){
        Product product = new Product(productDto.productName(), productDto.price());
        product.setCategory(category);
        return product;

    }



}