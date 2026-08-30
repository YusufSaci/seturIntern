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

    @Autowired
    public OrderRestController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<OrderDto> addOrdertoCustomer(@PathVariable Long customerId,
                                                       @RequestBody OrderDto orderDto){

        OrderDto newOrder = orderService.save(customerId, orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);

    }


}
