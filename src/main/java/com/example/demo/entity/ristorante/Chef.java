package com.example.demo.entity.ristorante;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chefs")
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "chef") // "La relazione è già gestita dal campo chef dentro Dish" --> dentro la classe Dish infatti hai un campo Chef chef
    private List<Dish> dishes;
}
