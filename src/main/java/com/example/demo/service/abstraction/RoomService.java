package com.example.demo.service.abstraction;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Customer;

import java.util.List;

public interface RoomService {
    List<CustomerResponseDTO> findAll();

    CustomerResponseDTO findById(Integer id);

    CustomerResponseDTO save(Customer customer);

    CustomerResponseDTO update(Integer id, CustomerRequestDTO customerRequestDTO);

    String deletedById(Integer id);
}
