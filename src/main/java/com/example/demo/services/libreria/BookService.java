package com.example.demo.services.libreria;

import com.example.demo.dto.BookResponseDTO;
import com.example.demo.entity.libreria.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    List<Book> findAll();

    BookResponseDTO findById(Integer id);

    Book save(Book book);

    void deletedById(Integer id);

    List<Book> findByGenre(String genre);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByAvailableTrue();

    List<Book> findBooksCheaperThan(BigDecimal price);
}
