package com.example.demo.repository.libreria;

import com.example.demo.entity.libreria.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    // Metodi CRUD già implementati da JPA
}
