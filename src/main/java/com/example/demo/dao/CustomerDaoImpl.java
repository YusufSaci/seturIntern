package com.example.demo.dao;


import com.example.demo.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerDaoImpl implements CustomerDao {

    private final EntityManager entityManager;

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer findById(long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public void deleteById(long id) {

        Customer customer =
                entityManager.find(Customer.class, id);

        if (customer != null) {
            entityManager.remove(customer);
        }
    }

    @Override
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }
}