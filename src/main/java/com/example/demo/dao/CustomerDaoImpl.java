package com.example.demo.dao;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Customer;
import com.example.demo.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao{


    private EntityManager entityManager;

    @Autowired
    public CustomerDaoImpl (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    
    @Override
    public void save(Customer customer){
       entityManager.persist(customer);
    }

    @Override
    public Customer findById(long id){
        return entityManager.find(Customer.class, id);
    }


    @Override
    public List<Customer> findAll(){
        TypedQuery<Customer> query = entityManager.createQuery("FROM Customer ", Customer.class);
        
        return query.getResultList();
    }

    @Override
    public void deleteById(long id){
        Customer customer = entityManager.find(Customer.class, id);

        if(customer != null){
            entityManager.remove(customer);
        }
    }

    @Override
    public Customer update(Customer customer){
        return entityManager.merge(customer);
    }


}