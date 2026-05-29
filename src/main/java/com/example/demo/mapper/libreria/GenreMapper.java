package com.example.demo.mapper.libreria;

import com.example.demo.dto.libreria.GenreDTO;
import com.example.demo.dto.libreria.requestdto.GenreRequestDTO;
import com.example.demo.dto.libreria.responsedto.GenreResponseDTO;
import com.example.demo.entity.libreria.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponseDTO entityToResponseDTO(Genre genre);
    List<GenreResponseDTO> entityToResponseDTO(List<Genre> genres);
    Genre requestDTOToEntity(GenreRequestDTO genreRequestDTO);
}
