package com.example.demo.services.ristorante;

import com.example.demo.dto.libreria.BookDTO;
import com.example.demo.dto.ristorante.CategoryRequestDTO;
import com.example.demo.dto.ristorante.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDTO> findAll();

    CategoryResponseDTO findById(Integer id);

    CategoryResponseDTO save(CategoryRequestDTO book);

    String deletedById(Integer id);
}
