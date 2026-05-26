package com.example.demo.controller.libreria;

import com.example.demo.dto.BookResponseDTO;
import com.example.demo.entity.libreria.Book;
import com.example.demo.services.libreria.BookService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponseDTO findById(@PathVariable Integer id) {
        return bookService.findById(id);
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
// Ignora qualsiasi ID arrivi dal client --> Crea sempre un nuovo record.
        book.setId(null);

        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public Book update(
            @PathVariable Integer id,
            @RequestBody Book book
    ) {

        book.setId(id);
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {

        bookService.deletedById(id);

        return "Deleted book id: " + id;
    }

    @GetMapping("/genre/{genre}")
    public List<Book> findByGenre(@PathVariable String genre) {
        return bookService.findByGenre(genre);
    }

    @GetMapping("/author")
    public List<Book> findByAuthor(@RequestParam String name) {

        return bookService.findByAuthorContaining(name);
    }

    @GetMapping("/available")
    public List<Book> availableBooks() {

        return bookService.findByAvailableTrue();
    }

    @GetMapping("/cheaper-than")
    public List<Book> cheaperThan(
            @RequestParam BigDecimal price) {

        return bookService.findBooksCheaperThan(price);
    }
}
