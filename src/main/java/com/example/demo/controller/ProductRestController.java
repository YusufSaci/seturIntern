package com.example.demo.controller;


import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductService productService;
    private ProductMapper productMapper;

    @Autowired
    public ProductRestController( ProductService productService,  ProductMapper productMapper,
         CategoryMapper categoryMapper){
        this.productService =  productService;
        this.productMapper =  productMapper;


    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addCategory(@RequestBody ProductDto productDto){

        ProductDto productDto = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateCategory(@PathVariable Long id,
                                                       @RequestBody CategoryDto productDto){

        ProductDto product = productService.update(productDto,id);
        return ResponseEntity.ok(product);

    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){

        ProductDto product = productService.findById(productId);

        return ResponseEntity.ok(product);

    }

    @GetMapping("/products")
    public ResponseEntity<List<CategoryDto>>  getCategories(){

        List<CategoryDto> categories =  categoryService.findAll();
        return ResponseEntity.ok(categories);

    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }


}
