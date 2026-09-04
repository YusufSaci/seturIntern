package com.example.demo.service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private  CustomerDao customerDao;
    private  CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, CustomerMapper customerMapper) {
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public void save(CustomerDto customerDto){
        Customer customer = customerMapper.toEntity(customerDto);
        customerDao.save(customer);
    }

    @Override
    public CustomerDto findById(long id){
        Customer customer = customerDao.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("customer not found");
        }

        return customerMapper.toDto(customer);
    }

    @Override
    public Customer findEntityById(long id){
        Customer customer = customerDao.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("customer not found");
        }

        return customer;
    }

    @Override
    public List<CustomerDto> findAll(){
        return customerDao.findAll().stream().map(customer -> customerMapper.toDto(customer)).toList();
    }

    @Override
    @Transactional
    public  void deleteById(long id){
        Customer customer = customerDao.deleteById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("customer not found");
        }

    }

    @Override
    @Transactional
    public CustomerDto update(CustomerDto customerDto, Long id){
        Customer customer = customerDao.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("customer not found");
        }
        customer = customerMapper.toEntity(customerDto);
        customer.setId(id);
        return customerMapper.toDto(customerDao.update(customer));
    }
}