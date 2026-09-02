package com.example.demo.controller;


import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryRestController( CategoryService categoryService, CustomerService customerService,
         CategoryMapper categoryMapper){
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;


    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){

        CategoryDto category = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);

    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                       @RequestBody CategoryDto categoryDto){

        CategoryDto category = categoryService.update(categoryDto,id);
        return ResponseEntity.ok(category);

    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId){

        CategoryDto category = categoryService.findById(categoryId);

        return ResponseEntity.ok(category);

    }

    @GetMapping("categories")
    public ResponseEntity<List<CategoryDto>>  getCategories(){

        List<CategoryDto> categories =  categoryService.findAll();
        return ResponseEntity.ok(categories);

    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }


}
