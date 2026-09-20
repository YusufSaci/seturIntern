package com.example.demo.service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerDao customerDao;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public OrderDto save(Long customerId, OrderDto dto) {

        Customer customer = getCustomerOrThrow(customerId);

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new ProductNotFoundException("product not found"));

        Order order = orderMapper.toEntity(dto, customer, product);

        customer.addOrder(order);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    @Override
    public OrderDto update(Long orderId, OrderDto dto) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("order not found"));

        Customer customer = getCustomerOrThrow(dto.customerId());

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new ProductNotFoundException("product not found"));

        order.setAmount(dto.amount());
        order.setCustomer(customer);
        order.setProduct(product);

        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto findById(long id) {

        return orderMapper.toDto(orderRepository.findById(id)
                        .orElseThrow(() -> new OrderNotFoundException("order not found"))
        );
    }

    @Override
    public List<OrderDto> findAll() {

        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public void deleteById(long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("order not found"));

        orderRepository.delete(order);
    }

    private Customer getCustomerOrThrow(Long id) {

        Customer customer = customerDao.findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException("customer not found");
        }

        return customer;
    }
}