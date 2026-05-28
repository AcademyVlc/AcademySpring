package com.example.demo.entity.ristorante;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<OrderRestaurant> orders;

    @OneToOne(mappedBy = "customer") // Messo per avere una relazione bidirezionale
    // --> "La relazione è già gestita dal campo customer dentro CustomerProfile"
    private CustomerProfile customerProfile;
}
