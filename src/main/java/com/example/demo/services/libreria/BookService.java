package com.example.demo.services.libreria;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.libreria.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    List<BookDTO> findAll();

    BookDTO findById(Integer id);

    BookDTO save(BookDTO book);

    void deletedById(Integer id);

    List<BookDTO> findByGenre(String genre);

    List<BookDTO> findByAuthorContaining(String author);

    List<BookDTO> findByAvailableTrue();

    List<BookDTO> findBooksCheaperThan(BigDecimal price);
}
