package com.example.demo.controller.libreria;

import com.example.demo.dto.libreria.BookDTO;
import com.example.demo.services.libreria.abstraction.BookService;
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

    // Ricerca per range di prezzo
    @GetMapping("/price-range")
    public List<BookDTO> findByPriceRange(@RequestParam BigDecimal min, @RequestParam BigDecimal max){
        return bookService.findByPriceRange(min, max);
    }

    // Libri disponibili per genere
    @GetMapping("/available-by-genre")
    public List<BookDTO> findAvailableByGenre(@RequestParam String genre){
        return bookService.findAvailableByGenre(genre);
    }

    // Ricerca globale per Titolo, Autore o Genere
    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam String keyword){
        return bookService.searchBooks(keyword);
    }

    // Libri più costosi di una certa soglia
    @GetMapping("/expensive-than")
    public List<BookDTO> expensiveThan(@RequestParam BigDecimal price){
        return bookService.expensiveThan(price);
    }

    // Libri per autore e per genere
    @GetMapping("/author-genre")
    public List<BookDTO> findByAuthorAndGenre(@RequestParam String author, @RequestParam String genre){
        return bookService.findByAuthorAndGenre(author, genre);
    }

    // Libri ordinati per prezzo crescente
    @GetMapping("/ordered-by-price")
    public List<BookDTO> findAllOrderByPriceAsc(){
        return bookService.findAllOrderByPriceAsc();
    }

    // Conta libri disponibili per genere
    @GetMapping("/count-available-by-genre")
    public Long countAvailableByGenre(@RequestParam String genre){
        return bookService.countAvailableByGenre(genre);
    }
}
