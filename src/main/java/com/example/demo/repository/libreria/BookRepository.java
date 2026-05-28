package com.example.demo.repository.libreria;

import com.example.demo.dto.libreria.BookDTO;
import com.example.demo.entity.libreria.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    // Prendi oggetti Book e chiamali con l’alias b
    @Query("""
            SELECT b 
            FROM Book b
            WHERE b.price BETWEEN :min AND :max
            """)
    List<Book> findByPriceRange(
            @Param("min") BigDecimal min,
            @Param("max") BigDecimal max
    );

    @Query("""
            SELECT b 
            FROM Book b
            WHERE b.available = true AND LOWER(b.genre.name) = LOWER(:genre)           
            """)
    List<Book> findAvailableByGenre(@Param("genre") String genreName);

    @Query("""
            SELECT b
            FROM Book b
            WHERE LOWER(b.title) LIKE LOWER(CONCAT('%',:keyword,'%')) 
                        OR LOWER(b.author.firstname) LIKE LOWER(CONCAT('%', :keyword,'%')) 
                                    OR LOWER(b.genre.name) LIKE LOWER(CONCAT('%', :keyword,'%')) 
            """)
    List<Book> searchBooks(@Param("keyword") String keyword);

    @Query("""
            SELECT b
            FROM Book b
            WHERE b.price > :price
            """)
    List<Book> expensiveThan(@Param("price") BigDecimal price);

    @Query("""
            SELECT b
            FROM Book b
            WHERE LOWER(b.author.firstname) LIKE LOWER(CONCAT('%', :author, '%')) 
            AND LOWER(b.genre.name) LIKE LOWER(CONCAT('%',:genre ,'%'))
            """)
    List<Book> findByAuthorAndGenre(@Param("author") String author, @Param("genre") String genre);

    @Query("""
            SELECT b
            FROM Book b
            ORDER BY b.price ASC
            """)
    List<Book> findAllOrderByPriceAsc();

    @Query("""
            SELECT COUNT(b)
            FROM Book b
            WHERE b.available = true 
            AND LOWER(b.genre.name) = LOWER(:genre) 
            """)
    Long countAvailableByGenre(@Param("genre") String genre);


}
