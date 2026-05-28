package com.example.demo.mapper.libreria;

import com.example.demo.dto.libreria.BookDTO;
import com.example.demo.dto.libreria.BookRequestDTO;
import com.example.demo.dto.libreria.BookResponseDTO;
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
    @Mapping(target = "genre", source = "genre.id") // Prendi book.getGenre().getId() e mettilo in bookDTO.setGenre(...)
    @Mapping(target = "price", source = "price")
    @Mapping(target = "available", source = "available")
    BookDTO entityToDTO(Book book);


    @Mapping(target = "author.id", source = "author") // Prendi bookDTO.getAuthor() e mettilo dentro book.getAuthor().setId(...)
    @Mapping(target = "genre.id", source = "genre") // Prendi bookDTO.getGenre() e mettilo dentro book.getGenre().setId(...)
    Book dtoToEntity(BookDTO bookDTO);

    List<BookDTO> entityToDTO(List<Book> books);
    List<Book> dtoToEntity(List<BookDTO> booksDTO);

    // MAPPATURA PER BookResponseDTO e BookRequestDTO
    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "genreName", source = "genre.name")
    BookResponseDTO entityToResponseDTO(Book book);

    @Mapping(target = "author.id", source = "authorId" )
    @Mapping(target = "genre.id", source = "genreId")
    Book requestDTOToEntity(BookRequestDTO bookRequestDTO);

    
//    default Integer returnVerifiedId(BookDTO bookDTO){
//        if(bookDTO.getId() == 3){
// Esempio per la expression dentro al Mapping
//        }
//        return 1;
//    }
}
