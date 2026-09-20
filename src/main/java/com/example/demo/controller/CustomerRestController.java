package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CustomerDetailDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerRestController{

    private final CustomerService customerService;
    private final JsonMapper jsonMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetailDto> getCustomer(@PathVariable Long id){

        CustomerDetailDto customer =customerService.findDetailById(id);
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

    @PutMapping("/{id}")
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