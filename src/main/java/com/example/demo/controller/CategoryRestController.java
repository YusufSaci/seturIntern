package com.example.demo.controller;

import com.example.demo.dto.CategoryDetailDto;
import com.example.demo.dto.CategoryDto;
import tools.jackson.databind.json.JsonMapper;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    private final JsonMapper jsonMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {

        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailDto> findDetailById(@PathVariable Long id) {

        return ResponseEntity.ok(categoryService.findDetailById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto dto) {

        CategoryDto saved = categoryService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id,
                                              @RequestBody CategoryDto dto) {

        return ResponseEntity.ok(categoryService.update(dto, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> patch(@PathVariable Long id,
                                             @RequestBody Map<String, Object> patch) {

        CategoryDto currentCategory = categoryService.findById(id);

        CategoryDto patchedCategory = jsonMapper.updateValue(currentCategory, patch);

        return ResponseEntity.ok(
                categoryService.update(
                        patchedCategory,
                        id
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}