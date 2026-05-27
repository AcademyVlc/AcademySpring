package com.example.demo.mapper;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.libreria.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", source = "id") // Entity to DTO --> book id - bookDTO id
//    @Mapping(target = "id", expression = "java(returnVerifiedId(book))")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author.id") // Prendi book.getAuthor().getId() e mettilo in bookDTO.setAuthor(...)
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "available", source = "available")
    BookDTO entityToDTO(Book book);

    @Mapping(target = "author.id", source = "author") // Prendi bookDTO.getAuthor() e mettilo dentro book.getAuthor().setId(...)
    Book dtoToEntity(BookDTO bookDTO);

    List<BookDTO> entityToDTO(List<Book> books);

    List<Book> dtoToEntity(List<BookDTO> booksDTO);

//    default Integer returnVerifiedId(BookDTO bookDTO){
//        if(bookDTO.getId() == 3){
//
//        }
//        return 1;
//    }
}
