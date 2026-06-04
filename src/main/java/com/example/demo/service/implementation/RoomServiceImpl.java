package com.example.demo.service.implementation;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Customer;
import com.example.demo.service.abstraction.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Override
    public List<CustomerResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public CustomerResponseDTO findById(Integer id) {
        return null;
    }

    @Override
    public CustomerResponseDTO save(Customer customer) {
        return null;
    }

    @Override
    public CustomerResponseDTO update(Integer id, CustomerRequestDTO customerRequestDTO) {
        return null;
    }

    @Override
    public String deletedById(Integer id) {
        return "";
    }
}
