package com.example.demo.services.ristorante.abstraction;

import com.example.demo.dto.ristorante.requestdto.CustomerRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> findAll();
    CustomerResponseDTO findById(Integer id);
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO update(Integer id, CustomerRequestDTO customerRequestDTO);
    String deletedById(Integer id);
}
