package com.example.demo.service.implementation;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Customer;
import com.example.demo.mapper.palestra.CustomerMapper;
import com.example.demo.repository.palestra.CustomerRepository;
import com.example.demo.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.entityToResponseDTO(customers);
    }

    @Override
    public CustomerResponseDTO findById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not founded"));
        return customerMapper.entityToResponseDTO(customer);
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
