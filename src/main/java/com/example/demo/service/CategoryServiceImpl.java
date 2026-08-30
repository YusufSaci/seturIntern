package com.example.demo.service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category save(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(long id){
         Optional<Category> category = categoryRepository.findById(id);

         if(category.isPresent()){
            return category.get();
         }

         throw new RuntimeException("category not found.");
    }

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public  void deleteById(long id){
        Category category = findById(id);

        List<Product> products = category.getProducts();

        for(Product tempProduct : products){
            tempProduct.setCategory(null);
        }
        
        categoryRepository.delete(category);
    }
}