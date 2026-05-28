package com.example.demo.entity.ristorante;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category") // "La relazione è già gestita dal campo category dentro Dish" --> dentro la classe Dish infatti hai un campo Category category
    private List<Dish> dishes;
}
