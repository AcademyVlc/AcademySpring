package com.example.demo.mapper.libreria;

import com.example.demo.dto.libreria.AuthorDTO;
import com.example.demo.dto.libreria.requestdto.AuthorRequestDTO;
import com.example.demo.dto.libreria.requestdto.BookRequestDTO;
import com.example.demo.dto.libreria.responsedto.AuthorResponseDTO;
import com.example.demo.dto.libreria.responsedto.BookResponseDTO;
import com.example.demo.entity.libreria.Author;
import com.example.demo.entity.libreria.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    // MAPPATURA PER BookResponseDTO e BookRequestDTO
    AuthorResponseDTO entityToResponseDTO(Author author);
    List<AuthorResponseDTO> entityToResponseDTO(List<Author> authors);

    @Mapping(target = "books", ignore = true)
    @Mapping(target = "author.id", ignore = true)
    Author requestDTOToEntity(AuthorRequestDTO authorRequestDTO);
//    List<Author> entityToResponseDTO(List<AuthorResponseDTO> authors);
}
