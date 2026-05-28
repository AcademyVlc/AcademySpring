package com.example.demo.services.libreria.abstraction;

import com.example.demo.dto.libreria.BookDTO;

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

    List<BookDTO> findByPriceRange(BigDecimal min, BigDecimal max);

    List<BookDTO> findAvailableByGenre(String genreName);

    List<BookDTO> searchBooks(String keyword);

    List<BookDTO> expensiveThan(BigDecimal price);

    List<BookDTO> findByAuthorAndGenre(String author, String genre);

    List<BookDTO> findAllOrderByPriceAsc();

    Long countAvailableByGenre(String genre);
}
