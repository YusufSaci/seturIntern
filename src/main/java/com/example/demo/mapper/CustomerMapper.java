package com.example.demo.mapper;

import com.example.demo.dto.CustomerDetailDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    CustomerDetailDto toDetailDto(Customer customer);

    Customer toEntity(CustomerDto dto);
}