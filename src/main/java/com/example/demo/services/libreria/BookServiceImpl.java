package com.example.demo.services.libreria;

import com.example.demo.dto.BookDTO;
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
}
