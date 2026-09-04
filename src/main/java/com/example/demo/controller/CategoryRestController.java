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
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private JsonMapper jsonMapper;

    @Autowired
    public CategoryRestController( CategoryService categoryService, CustomerService customerService,
         CategoryMapper categoryMapper, JsonMapper jsonMapper){
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.jsonMapper = jsonMapper;


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

    @PatchMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategoryWithPatch(@PathVariable Long id,
                                                      @RequestBody Map<String,Object> patch){

        CategoryDto oldCategory = categoryService.findById(id);
        CategoryDto updatedCategory = jsonMapper.updateValue(oldCategory,patch);
        CategoryDto category = categoryService.update(updatedCategory,id);

        return ResponseEntity.ok(category);

    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }


}
