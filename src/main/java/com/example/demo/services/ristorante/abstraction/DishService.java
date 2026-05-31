package com.example.demo.services.ristorante.abstraction;

import com.example.demo.dto.ristorante.requestdto.CustomerRequestDTO;
import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerResponseDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;

import java.util.List;

public interface DishService {

    List<DishResponseDTO> findAll();
    DishResponseDTO findById(Integer id);
    DishResponseDTO save(DishRequestDTO dishRequestDTO);
    DishResponseDTO update(Integer id, DishRequestDTO dishRequestDTO);
    String deletedById(Integer id);
}
