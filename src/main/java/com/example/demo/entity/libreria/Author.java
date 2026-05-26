package com.example.demo.entity.libreria;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name")
    private String firstname;

    @Column(name="last_name")
    private String lastname;

    @Column(name="birthdate")
    private LocalDate birthdate;

    @Column(name="elimination_date")
    private LocalDate eliminationDate;

    @OneToMany(mappedBy = "author") // proprietà della classe che rappresenta la corrispondenza
    private List<Book> books;
}