package com.example.demo.services.libreria.abstraction;

import com.example.demo.dto.libreria.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> findAll();

    GenreDTO findById(Integer id);

    GenreDTO save(GenreDTO genreDTO);

    void deletedById(Integer id);
}
