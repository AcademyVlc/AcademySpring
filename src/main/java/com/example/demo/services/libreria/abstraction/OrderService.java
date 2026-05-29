package com.example.demo.services.libreria.abstraction;

import com.example.demo.dto.libreria.OrderDTO;
import com.example.demo.dto.libreria.requestdto.OrderRequestDTO;
import com.example.demo.dto.libreria.responsedto.OrderResponseDTO;

import java.util.List;

public interface OrderService{
    // JPA implementa i metodi crud da solo

    List<OrderResponseDTO> findAll();

    OrderResponseDTO findById(Integer id);

    OrderResponseDTO save(OrderRequestDTO orderRequestDTO);

    void deletedById(Integer id);
}
