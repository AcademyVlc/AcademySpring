package com.example.demo.services.libreria;

import com.example.demo.entity.libreria.Author;
import com.example.demo.repository.libreria.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Author not foundend with id - " + id)
                );
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deletedById(Integer id) {
        authorRepository.deleteById(id);
    }
}
