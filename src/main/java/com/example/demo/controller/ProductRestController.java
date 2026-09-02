package com.example.demo.controller;



import com.example.demo.dto.ProductDto;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public ProductRestController( ProductService productService){
        this.productService =  productService;

    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addCategory(@RequestBody ProductDto productDto){

        ProductDto product = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateCategory(@PathVariable Long id,
                                                       @RequestBody ProductDto productDto){

        ProductDto product = productService.update(id ,productDto);
        return ResponseEntity.ok(product);

    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){

        ProductDto product = productService.findById(productId);

        return ResponseEntity.ok(product);

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>>  getCategories(){

        List<ProductDto> products =  productService.findAll();
        return ResponseEntity.ok(products);

    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }


}
