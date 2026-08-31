package com.example.demo.controller;


import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    private OrderService orderService;
    private CustomerService customerService
    private OrderMapper orderMapper;

    @Autowired
    public OrderRestController(OrderService orderService, CustomerService customerService,
         OrderMapper orderMapper){
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderMapper = orderMapper;


    }

    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<OrderDto> addOrdertoCustomer(@PathVariable Long customerId,
                                                       @RequestBody OrderDto orderDto){

        OrderDto newOrder = orderService.save(customerId, orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);

    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<List<OrderDto>> getCustomerOrderById(@PathVariable Long customerId){

        Customer customer  = customerService.findEntityById()
        List<OrderDto> orders = customer.getOrders().stream()
                .map(order -> orderMapper.toDto(order))
                .toList();

        return ResponseEntity.ok(orders);

    }

    @GetMapping("/customers/orders")
    public ResponseEntity<List<OrderDto>> getCustomerOrderById(){

        List<OrderDto> orders = orderService.findAll();
        return ResponseEntity.ok(orders);

    }

    @DeleteMapping("/customers/orders/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        orderService.deleteById();
        return ResponseEntity.noContent().build();
    }


}
