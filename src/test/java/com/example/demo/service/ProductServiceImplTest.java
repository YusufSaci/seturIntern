package com.example.demo.service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void shouldSaveProductSuccessfully() {

        Category category = new Category();

        ProductDto dto =
                new ProductDto(
                        null,
                        "Laptop",
                        50000,
                        1L,
                        null
                );

        Product product = new Product();
        Product savedProduct = new Product();

        ProductDto responseDto =
                new ProductDto(
                        1L,
                        "Laptop",
                        50000,
                        1L,
                        "Electronics"
                );

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        when(productMapper.toEntity(dto, category))
                .thenReturn(product);

        when(productRepository.save(product))
                .thenReturn(savedProduct);

        when(productMapper.toDto(savedProduct))
                .thenReturn(responseDto);

        ProductDto result = productService.save(dto);

        assertNotNull(result);
        assertEquals("Laptop", result.productName());

        verify(categoryRepository).findById(1L);
        verify(productRepository).save(product);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFoundForSave() {

        ProductDto dto =
                new ProductDto(
                        null,
                        "Laptop",
                        50000,
                        1L,
                        null
                );

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                () -> productService.save(dto)
        );

        verify(productRepository, never())
                .save(any());
    }

    @Test
    void shouldUpdateProductSuccessfully() {

        Long id = 1L;

        ProductDto dto =
                new ProductDto(
                        id,
                        "Updated Laptop",
                        60000,
                        1L,
                        null
                );

        Product oldProduct = new Product();
        Category category = new Category();

        Product product = new Product();
        Product updatedProduct = new Product();

        ProductDto responseDto =
                new ProductDto(
                        id,
                        "Updated Laptop",
                        60000,
                        1L,
                        "Electronics"
                );

        when(productRepository.findById(id))
                .thenReturn(Optional.of(oldProduct));

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));


        when(productMapper.toDto(any(Product.class)))
                .thenReturn(responseDto);

        ProductDto result =
                productService.update(id, dto);

        assertNotNull(result);
        assertEquals(
                "Updated Laptop",
                result.productName()
        );

        verify(productRepository).findById(id);
        verify(categoryRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFoundForUpdate() {

        Long id = 1L;

        ProductDto dto =
                new ProductDto(
                        null,
                        "Laptop",
                        50000,
                        1L,
                        null
                );

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.update(id, dto)
        );

        verify(productRepository, never())
                .save(any());
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFoundForUpdate() {

        Long id = 1L;

        Product oldProduct = new Product();

        ProductDto dto =
                new ProductDto(
                        id,
                        "Laptop",
                        50000,
                        1L,
                        null
                );

        when(productRepository.findById(id))
                .thenReturn(Optional.of(oldProduct));

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                () -> productService.update(id, dto)
        );

        verify(productRepository, never())
                .save(any());
    }

    @Test
    void shouldFindProductById() {

        Long id = 1L;

        Product product = new Product();

        ProductDto dto =
                new ProductDto(
                        id,
                        "Laptop",
                        50000,
                        1L,
                        "Electronics"
                );

        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        when(productMapper.toDto(product))
                .thenReturn(dto);

        ProductDto result =
                productService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals(
                "Laptop",
                result.productName()
        );
    }

    @Test
    void shouldThrowExceptionWhenProductNotFoundForFindById() {

        Long id = 1L;

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.findById(id)
        );
    }

    @Test
    void shouldReturnAllProducts() {

        Product product1 = new Product();
        Product product2 = new Product();

        ProductDto dto1 =
                new ProductDto(
                        1L,
                        "Laptop",
                        50000,
                        1L,
                        "Electronics"
                );

        ProductDto dto2 =
                new ProductDto(
                        2L,
                        "Book",
                        100,
                        2L,
                        "Books"
                );

        when(productRepository.findAll())
                .thenReturn(List.of(product1, product2));

        when(productMapper.toDto(product1))
                .thenReturn(dto1);

        when(productMapper.toDto(product2))
                .thenReturn(dto2);

        List<ProductDto> result =
                productService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void shouldDeleteProductSuccessfully() {

        Long id = 1L;

        Product product = new Product();

        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        productService.deleteById(id);

        verify(productRepository).delete(product);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFoundForDelete() {

        Long id = 1L;

        when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.deleteById(id)
        );

        verify(productRepository, never())
                .delete(any());
    }
}