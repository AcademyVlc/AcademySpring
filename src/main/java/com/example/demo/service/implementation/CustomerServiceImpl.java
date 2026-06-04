package com.example.demo.service.implementation;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Customer;
import com.example.demo.service.abstraction.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<CustomerResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public CustomerResponseDTO findById() {
        return null;
    }

    @Override
    public CustomerResponseDTO save(Customer customer) {
        return null;
    }

    @Override
    public CustomerResponseDTO update(Customer customer) {
        return null;
    }

    @Override
    public CustomerResponseDTO deletedById(Integer id, CustomerRequestDTO customerRequestDTO) {
        return null;
    }
}
