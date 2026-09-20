package com.example.demo.service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.CustomerDetailDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerDto save(CustomerDto dto) {

        Customer customer = customerMapper.toEntity(dto);

        customerDao.save(customer);

        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDetailDto findDetailById(Long id) {

        Customer customer = getCustomerOrThrow(id);
        return customerMapper.toDetailDto(customer);
    }

    @Override
    public CustomerDto findById(Long id) {

        Customer customer = getCustomerOrThrow(id);

        return customerMapper.toDto(customer);
    }

    @Override
    public Customer findEntityById(Long id) {

        return getCustomerOrThrow(id);
    }

    @Override
    public List<CustomerDto> findAll() {

        return customerDao.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        Customer customer = getCustomerOrThrow(id);

        customerDao.deleteById(customer.getId());
    }

    @Override
    @Transactional
    public CustomerDto update(CustomerDto dto, Long id) {

        Customer customer = getCustomerOrThrow(id);

        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setAge(dto.age());

        return customerMapper.toDto(customerDao.update(customer));
    }

    private Customer getCustomerOrThrow(Long id) {

        Customer customer = customerDao.findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException("customer not found");
        }

        return customer;
    }
}
