package com.example.demo.entity.libreria;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="qty_products")
    private Integer qty;

    @Column(name="total_price")
    private Double totalPrice;

    @ManyToMany
    @JoinTable(
            name = "order_to_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;


}
