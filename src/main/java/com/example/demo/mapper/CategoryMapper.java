package com.example.demo.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper{

    private ProductMapper productMapper;

    public CategoryMapper(ProductMapper productMapper){
        this.productMapper = productMapper;
    }


    public CategoryDto toDto(Category category){
        List<ProductDto> productsDto = null;

        if(category.getProducts() != null) {
            productsDto = category.getProducts().stream()
                    .map(product -> productMapper.toDto(product)).toList();
        }

        return new CategoryDto(
            category.getId(),
            category.getCategoryName(),
            productsDto

        );
    }

    public Category toEntity(CategoryDto categoryDto){

        Category category = new Category(categoryDto.categoryName());
        return  category;
       


    }

}