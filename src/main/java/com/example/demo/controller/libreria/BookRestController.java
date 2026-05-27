package com.example.demo.controller.libreria;

import com.example.demo.dto.libreria.BookDTO;
import com.example.demo.services.libreria.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Integer id) {
        return bookService.findById(id);
    }

    @PostMapping
    public BookDTO save(@RequestBody BookDTO bookDTO) {
// Ignora qualsiasi ID arrivi dal client --> Crea sempre un nuovo record.
        bookDTO.setId(null);
        return bookService.save(bookDTO);
    }

    @PutMapping("/{id}")
    public BookDTO update(
            @PathVariable Integer id,
            @RequestBody BookDTO bookDTO
    ) {

        bookDTO.setId(id);
        return bookService.save(bookDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {

        bookService.deletedById(id);

        return "Deleted book id: " + id;
    }

    @GetMapping("/genre/{genre}")
    public List<BookDTO> findByGenre(@PathVariable String genre) {
        return bookService.findByGenre(genre);
    }

    @GetMapping("/author")
    public List<BookDTO> findByAuthor(@RequestParam String name) {

        return bookService.findByAuthorContaining(name);
    }

    @GetMapping("/available")
    public List<BookDTO> availableBooks() {

        return bookService.findByAvailableTrue();
    }

    @GetMapping("/cheaper-than")
    public List<BookDTO> cheaperThan(
            @RequestParam BigDecimal price) {

        return bookService.findBooksCheaperThan(price);
    }
}
