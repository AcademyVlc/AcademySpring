package com.example.demo.entity.libreria;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne // TODO - Many to one o Many to many? ----> Aggiunto Questo
    @JoinColumn(name = "genre")
    private Genre genre;

    @Column(name = "price")
    private Double price;

    @Column(name = "available")
    private Boolean available;

    @Column(name="elimination_date")
    private LocalDate eliminationDate;

    @Column(name="update_date")
    private LocalDate updateDate;

    @ManyToMany(mappedBy = "books")
    private List<Order> orders;
}
