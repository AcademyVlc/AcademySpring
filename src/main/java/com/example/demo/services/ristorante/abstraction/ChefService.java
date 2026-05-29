package com.example.demo.services.ristorante.abstraction;

import com.example.demo.dto.ristorante.requestdto.ChefRequestDTO;
import com.example.demo.dto.ristorante.responsedto.ChefResponseDTO;

import java.util.List;

public interface ChefService {

    List<ChefResponseDTO> findAll();

    ChefResponseDTO findById(Integer id);

    ChefResponseDTO save(ChefRequestDTO chefRequestDTO);

    String deletedById(Integer id);
}
