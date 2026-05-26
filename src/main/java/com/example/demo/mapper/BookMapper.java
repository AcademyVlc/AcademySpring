package com.example.demo.mapper;

import com.example.demo.dto.BookResponseDTO;
import com.example.demo.entity.libreria.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "available", source = "available")
    BookResponseDTO entityToDTO(Book book);
}
