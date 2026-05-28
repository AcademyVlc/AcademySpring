package com.example.demo.entity.ristorante;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "available")
    private boolean available;

    @ManyToOne
    @JoinColumn (name = "category_id") // @JoinColumn deve indicare: la colonna FK presente nella tabella corrente
    private Category category;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;
}
