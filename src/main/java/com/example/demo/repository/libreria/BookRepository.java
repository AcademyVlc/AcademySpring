package com.example.demo.repository.libreria;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.libreria.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // CRUD base messi e implementati già da JPA

    List<Book> findByGenre(String genre);

    List<Book> findByAuthorContaining(String author); // id e name -> Rifarlo con findByAuthorId e findByAuthorName

    List<Book> findByAvailableTrue();

    // Perchè Book b
    @Query("""
            SELECT b 
            FROM Book b
            WHERE b.price < :price
            """)
    List<Book> findBooksCheaperThan(BigDecimal price);


}
