package com.example.demo.mapper;

import com.example.demo.dto.AuthorDTO;
import com.example.demo.entity.libreria.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "birthdate", source = "birthdate")
    @Mapping(target = "eliminationDate", source = "eliminationDate")
    AuthorDTO entityToDTO(Author author);
    Author dtoToEntity(AuthorDTO authorDTO);

    List<AuthorDTO> entityToDTO(List<Author> authors);
    List<Author> dtoToEntity(List<AuthorDTO> authorsDTO);
}
