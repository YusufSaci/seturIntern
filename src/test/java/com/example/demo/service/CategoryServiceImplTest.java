package com.example.demo.service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.mapper.CategoryMapper;
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
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void shouldSaveCategorySuccessfully() {

        CategoryDto dto =
                new CategoryDto(null, "Electronics");

        Category category = new Category();
        Category savedCategory = new Category();

        CategoryDto responseDto =
                new CategoryDto(1L, "Electronics");

        when(categoryMapper.toEntity(dto))
                .thenReturn(category);

        when(categoryRepository.save(category))
                .thenReturn(savedCategory);

        when(categoryMapper.toDto(savedCategory))
                .thenReturn(responseDto);

        CategoryDto result = categoryService.save(dto);

        assertNotNull(result);
        assertEquals("Electronics", result.categoryName());

        verify(categoryRepository).save(category);
    }

    @Test
    void shouldUpdateCategorySuccessfully() {

        Long id = 1L;

        CategoryDto dto =
                new CategoryDto(id, "Updated Category");

        Category oldCategory = new Category();

        CategoryDto responseDto =
                new CategoryDto(id, "Updated Category");

        when(categoryRepository.findById(id))
                .thenReturn(Optional.of(oldCategory));


        when(categoryMapper.toDto(any(Category.class)))
                .thenReturn(responseDto);

        CategoryDto result = categoryService.update(dto, id);

        assertNotNull(result);
        assertEquals("Updated Category",
                result.categoryName());

        verify(categoryRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFoundForUpdate() {

        Long id = 1L;

        when(categoryRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                () -> categoryService.update(
                        new CategoryDto(null, "Test"),
                        id
                )
        );
    }

    @Test
    void shouldFindCategoryById() {

        Long id = 1L;

        Category category = new Category();

        CategoryDto dto =
                new CategoryDto(id, "Electronics");

        when(categoryRepository.findById(id))
                .thenReturn(Optional.of(category));

        when(categoryMapper.toDto(category))
                .thenReturn(dto);

        CategoryDto result =
                categoryService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Electronics",
                result.categoryName());
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFoundForFindById() {

        Long id = 1L;

        when(categoryRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                () -> categoryService.findById(id)
        );
    }

    @Test
    void shouldReturnAllCategories() {

        Category category1 = new Category();
        Category category2 = new Category();

        CategoryDto dto1 =
                new CategoryDto(1L, "Electronics");

        CategoryDto dto2 =
                new CategoryDto(2L, "Books");

        when(categoryRepository.findAll())
                .thenReturn(List.of(category1, category2));

        when(categoryMapper.toDto(category1))
                .thenReturn(dto1);

        when(categoryMapper.toDto(category2))
                .thenReturn(dto2);

        List<CategoryDto> result =
                categoryService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void shouldDeleteCategorySuccessfully() {

        Long id = 1L;

        Category category = new Category();

        Product product1 = new Product();
        Product product2 = new Product();

        product1.setCategory(category);
        product2.setCategory(category);

        category.setProducts(
                List.of(product1, product2)
        );

        when(categoryRepository.findById(id))
                .thenReturn(Optional.of(category));

        categoryService.deleteById(id);

        assertNull(product1.getCategory());
        assertNull(product2.getCategory());

        verify(categoryRepository).delete(category);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFoundForDelete() {

        Long id = 1L;

        when(categoryRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                () -> categoryService.deleteById(id)
        );

        verify(categoryRepository, never())
                .delete(any());
    }
}