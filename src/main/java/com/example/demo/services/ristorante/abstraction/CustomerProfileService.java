package com.example.demo.services.ristorante.abstraction;

import com.example.demo.dto.ristorante.requestdto.CustomerProfileRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerProfileResponseDTO;

import java.util.List;

public interface CustomerProfileService {

    List<CustomerProfileResponseDTO> findAll();

    CustomerProfileResponseDTO findById(Integer id);

    CustomerProfileResponseDTO save(CustomerProfileRequestDTO customerProfileRequestDTO);

    CustomerProfileResponseDTO update(Integer id, CustomerProfileRequestDTO customerProfileRequestDTO);

    String deletedById(Integer id);
}
