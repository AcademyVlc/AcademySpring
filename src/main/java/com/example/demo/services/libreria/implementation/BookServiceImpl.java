package com.example.demo.services.libreria.implementation;

import com.example.demo.dto.libreria.BookDTO;
import com.example.demo.entity.libreria.Book;
import com.example.demo.mapper.libreria.BookMapper;
import com.example.demo.repository.libreria.BookRepository;
import com.example.demo.services.libreria.abstraction.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.entityToDTO(books);
    }

    @Override
    public BookDTO findById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not founded with id - " + id));
        BookDTO responseDTO = bookMapper.entityToDTO(book);
        return responseDTO;
    }

    // Save riceve un DTO e lo converte in Entity
    @Override
    public BookDTO save(BookDTO bookDTO) {

        Book book = bookMapper.dtoToEntity(bookDTO);

        Book savedBook = bookRepository.save(book);

        return bookMapper.entityToDTO(savedBook);
    }

    @Override
    public void deletedById(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not founded, with id - " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> findByGenre(String genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        return bookMapper.entityToDTO(books);
    }

    @Override
    public List<BookDTO> findByAuthorContaining(String author) {
        List<Book> books = bookRepository.findByAuthorContaining(author);
        return bookMapper.entityToDTO(books);
    }

    @Override
    public List<BookDTO> findByAvailableTrue() {
        List<Book> books = bookRepository.findByAvailableTrue();
        return bookMapper.entityToDTO(books);
    }

    @Override
    public List<BookDTO> findBooksCheaperThan(BigDecimal price) {
        List<Book> books = bookRepository.findBooksCheaperThan(price);
        return bookMapper.entityToDTO(books);
    }

    @Override
    public List<BookDTO> findByPriceRange(BigDecimal min, BigDecimal max) {
        List<Book> books = bookRepository.findByPriceRange(min, max);
        return bookMapper.entityToDTO(books);
    }

    @Override
    public List<BookDTO> findAvailableByGenre(String genreName) {
        List<Book> availableByGenre = bookRepository.findAvailableByGenre(genreName);
        return bookMapper.entityToDTO(availableByGenre);
    }

    @Override
    public List<BookDTO> searchBooks(String keyword) {
        List<Book> books = bookRepository.searchBooks(keyword);
        return bookMapper.entityToDTO(books);
    }

    @Override
    public List<BookDTO> expensiveThan(BigDecimal price) {
        List<Book> books = bookRepository.expensiveThan(price);
        return bookMapper.entityToDTO(books);
    }

    @Override
    public List<BookDTO> findByAuthorAndGenre(String author, String genre) {
        List<Book> books = bookRepository.findByAuthorAndGenre(author, genre);
        return bookMapper.entityToDTO(books);
    }

    @Override
    public List<BookDTO> findAllOrderByPriceAsc() {
        List<Book> books = bookRepository.findAllOrderByPriceAsc();
        return bookMapper.entityToDTO(books);
    }

    @Override
    public Long countAvailableByGenre(String genre) {
        Long booksCount = bookRepository.countAvailableByGenre(genre);
        return booksCount;
    }
}
