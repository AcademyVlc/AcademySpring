package com.example.demo.services.libreria.abstraction;

import com.example.demo.dto.libreria.AuthorDTO;
import com.example.demo.dto.libreria.requestdto.AuthorRequestDTO;
import com.example.demo.dto.libreria.responsedto.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorResponseDTO> findAll();

    AuthorResponseDTO findById(Integer id);

    AuthorResponseDTO save(AuthorRequestDTO authorRequestDTO);

    void deletedById(Integer id);
}
