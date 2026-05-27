package com.example.demo.services.libreria;

import com.example.demo.dto.libreria.AuthorDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> findAll();

    AuthorDTO findById(Integer id);

    AuthorDTO save(AuthorDTO authorDTO);

    void deletedById(Integer id);
}
