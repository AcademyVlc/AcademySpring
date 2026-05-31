package com.example.demo.services.ristorante.abstraction;

import com.example.demo.dto.ristorante.requestdto.CategoryRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDTO> findAll();

    CategoryResponseDTO findById(Integer id);

    CategoryResponseDTO save(CategoryRequestDTO book);

    CategoryResponseDTO update(Integer id, CategoryRequestDTO categoryRequestDTO);

    String deletedById(Integer id);
}
