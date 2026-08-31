package com.example.demo.service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerDao customerDao;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerDao customerDao,
                            ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerDao = customerDao;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDto save(Long customerId,OrderDto orderDto) {
        Customer customer = customerDao.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("customer not found");
        }

        Product product = productRepository.findById(orderDto.productId())
                .orElseThrow(() -> new RuntimeException("product not found"));

        Order order = orderMapper.toEntity(orderDto, customer, product);
        customer.addOrder(order);
        order = orderRepository.save(order);

        return orderMapper.toDto(order);
    }


    @Override
    @Transactional
    public OrderDto update(Long orderId,OrderDto orderDto) {
        Customer customer = customerDao.findById(orderDto.customerId());
        if (customer == null) {
            throw new RuntimeException("customer not found");
        }

        Product product = productRepository.findById(orderDto.productId())
                .orElseThrow(() -> new RuntimeException("product not found"));

        Order order = orderMapper.toEntity(orderDto, customer, product);
        order.setId(orderId);

        order = orderRepository.save(order);

        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto findById(long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order not found") );

        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> findAll(){

        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional // buna bi bak list de güncelleniyor mu 
    public  void deleteById(long id){
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new RuntimeException("order not found") );
        
        orderRepository.delete(order);

    }




}