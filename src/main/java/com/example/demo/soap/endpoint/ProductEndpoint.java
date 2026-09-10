package com.example.demo.soap.endpoint;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import com.example.demo.soap.generated.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/product";

    private final ProductService productService;

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "GetProductRequest"
    )
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {

        ProductDto productDto = productService.findById(request.getId());

        GetProductResponse response = new GetProductResponse();

        response.setId(productDto.id());
        response.setName(productDto.productName());
        response.setPrice(productDto.price());

        Category category = new Category();

        category.setId(productDto.categoryId());
        category.setName(productDto.categoryName());

        response.setCategory(category);

        return response;
    }

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "CreateProductRequest"
    )
    @ResponsePayload
    public CreateProductResponse createProduct(@RequestPayload CreateProductRequest request) {

        ProductDto productDto = new ProductDto(
                null,
                request.getProductName(),
                request.getPrice(),
                request.getCategoryId(),
                request.getCategoryName()
        );

        ProductDto savedProductDto = productService.save(productDto);


        CreateProductResponse response = new CreateProductResponse();

        response.setId(savedProductDto.id());
        response.setProductName(productDto.productName());
        response.setPrice(productDto.price());

        Category category = new Category();

        category.setId(productDto.categoryId());
        category.setName(productDto.categoryName());

        response.setCategory(category);

        return response;
    }
}