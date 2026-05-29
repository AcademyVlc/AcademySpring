package com.example.demo.services.libreria.abstraction;

import com.example.demo.dto.libreria.GenreDTO;
import com.example.demo.dto.libreria.requestdto.GenreRequestDTO;
import com.example.demo.dto.libreria.responsedto.GenreResponseDTO;

import java.util.List;

public interface GenreService {
    List<GenreResponseDTO> findAll();

    GenreResponseDTO findById(Integer id);

    GenreResponseDTO save(GenreRequestDTO genreRequestDTO);

    void deletedById(Integer id);
}
