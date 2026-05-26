package com.example.demo.services.libreria;

import com.example.demo.entity.libreria.Author;
import com.example.demo.entity.libreria.Book;
import com.example.demo.repository.libreria.AuthorRepository;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(Integer id);

    Author save(Author author);

    void deletedById(Integer id);
}
