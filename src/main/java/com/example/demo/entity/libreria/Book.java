package com.example.demo.entity.libreria;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
// Potrei non mettere Column ma come primo esercizio ci sta   tenerlo
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name="author")
    private Author author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "price")
    private Double price;

    @Column(name = "available")
    private Boolean available;
}
