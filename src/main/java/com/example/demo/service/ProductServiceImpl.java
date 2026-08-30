package com.example.demo.service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private  ProductRepository  productRepository;

    @Autowired
    public  ProductServiceImpl( ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product save(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product findById(long id){
         Optional<Product > product = productRepository.findById(id);

         if(product.isPresent()){
            return product.get();
         }

         throw new RuntimeException("product not found.");
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public  void deleteById(long id){
        Product product = findById(id); 
        productRepository.delete(product);
    }
}