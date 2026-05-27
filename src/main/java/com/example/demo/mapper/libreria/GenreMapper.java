package com.example.demo.mapper.libreria;

import com.example.demo.dto.libreria.GenreDTO;
import com.example.demo.entity.libreria.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    GenreDTO entityToDTO(Genre genre);
    Genre dtoToEntity(GenreDTO genreDTO);

    List<GenreDTO> entityToDTO(List<Genre> genre);
    List<Genre> dtoToEntity(List<GenreDTO> genreDTO);
}
