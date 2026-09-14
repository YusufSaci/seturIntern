package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    ProductDto toDto(Product product);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "category", source = "category")
    Product toEntity(ProductDto dto, Category category);

    List<ProductDto> toListDto(List<Product> products);

    List<Product> toList(List<ProductDto> productsDto);

}

