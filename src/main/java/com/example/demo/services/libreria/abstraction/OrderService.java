package com.example.demo.services.libreria.abstraction;

import com.example.demo.dto.libreria.OrderDTO;

import java.util.List;

public interface OrderService{
    // JPA implementa i metodi crud da solo

    List<OrderDTO> findAll();

    OrderDTO findById(Integer id);

    OrderDTO save(OrderDTO orderDTO);

    void deletedById(Integer id);
}
