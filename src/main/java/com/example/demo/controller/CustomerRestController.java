package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")

public class CustomerRestController{

    private CustomerService customerService;
    private JsonMapper jsonMapper;


    @Autowired
    public CustomerRestController(CustomerService customerService,JsonMapper jsonMapper) {
        this.customerService = customerService;
        this.jsonMapper = jsonMapper;
    }   


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id){

        CustomerDto customer =customerService.findById(id);
        return ResponseEntity.ok(customer);

    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<CustomerDto> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDto customer) {
        customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customer){
        CustomerDto updatedCustomer = customerService.update(customer,id);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomerWithPatch(@PathVariable Long id,
                                                               @RequestBody Map<String,Object> patch){

        CustomerDto oldCustomer = customerService.findById(id);
        CustomerDto updatedCustomer= jsonMapper.updateValue(oldCustomer,patch);
        CustomerDto category = customerService.update(updatedCustomer,id);

        return ResponseEntity.ok(category);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}