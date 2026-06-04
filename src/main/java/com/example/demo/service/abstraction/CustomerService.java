package com.example.demo.service.abstraction;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> findAll();
    CustomerResponseDTO findById();
    CustomerResponseDTO save(Customer customer);
    CustomerResponseDTO update(Customer customer);
    CustomerResponseDTO deletedById(Integer id, CustomerRequestDTO customerRequestDTO);
}
