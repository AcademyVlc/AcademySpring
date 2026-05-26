package com.example.demo.services.libreria;

import com.example.demo.dto.BookResponseDTO;
import com.example.demo.entity.libreria.Book;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.libreria.BookRepository;
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
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public BookResponseDTO findById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not founded with id - " + id));
        BookResponseDTO responseDTO = bookMapper.entityToDTO(book);
        return responseDTO;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deletedById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> findByAuthorContaining(String author) {
        return bookRepository.findByAuthorContaining(author);
    }

    @Override
    public List<Book> findByAvailableTrue() {
        return bookRepository.findByAvailableTrue();
    }

    @Override
    public List<Book> findBooksCheaperThan(BigDecimal price) {
        return bookRepository.findBooksCheaperThan(price);
    }
}
