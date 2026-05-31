package com.example.demo.services.ristorante.abstraction;

import com.example.demo.dto.ristorante.requestdto.CustomerRequestDTO;
import com.example.demo.dto.ristorante.requestdto.OrderRestaurantRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerResponseDTO;
import com.example.demo.dto.ristorante.responsedto.OrderRestaurantResponseDTO;

import java.util.List;

public interface OrderRestaurantService {

    List<OrderRestaurantResponseDTO> findAll();
    OrderRestaurantResponseDTO findById(Integer id);
    OrderRestaurantResponseDTO save(OrderRestaurantRequestDTO orderRestaurantRequestDTO);
    OrderRestaurantResponseDTO update(Integer id, OrderRestaurantRequestDTO orderRestaurantRequestDTO);
    String deletedById(Integer id);
}
