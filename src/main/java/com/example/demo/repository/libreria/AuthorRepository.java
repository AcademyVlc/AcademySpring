package com.example.demo.repository.libreria;

import com.example.demo.entity.libreria.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // CRUD BASE già messi e implementati da JPA
}
