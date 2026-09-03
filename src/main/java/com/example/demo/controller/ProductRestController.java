package com.example.demo.controller;



import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductDto;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductService productService;
    private JsonMapper jsonMapper;


    @Autowired
    public ProductRestController( ProductService productService,JsonMapper jsonMapper){
        this.productService =  productService;
        this.jsonMapper = jsonMapper;


    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){

        ProductDto product = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                       @RequestBody ProductDto productDto){

        ProductDto product = productService.update(id ,productDto);
        return ResponseEntity.ok(product);

    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProductWithPatch(@PathVariable Long id,
                                                            @RequestBody Map<String,Object> patch){

        ProductDto oldProduct= productService.findById(id);
        ProductDto updatedProduct= jsonMapper.updateValue(oldProduct,patch);
        ProductDto category = productService.update(id,updatedProduct);

        return ResponseEntity.ok(category);

    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){

        ProductDto product = productService.findById(productId);

        return ResponseEntity.ok(product);

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>>  getProducts(){

        List<ProductDto> products =  productService.findAll();
        return ResponseEntity.ok(products);

    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }


}
