package com.example.demo.entity.palestra;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // NB: meglio bloccare prima lato Java e lasciare il DB come seconda protezione.
    @NotBlank(message = "customer's firstname not present") // Servono ad intercettare i dati prima di salvarli nel database --> JAVA firstname NON vuoto
    @Column(nullable = false) // DATABASE --> firstname non nullo --> Aggiunto perchè cosi creo coerenza logica tra i 2 lati (Java e DB)
    private String firstname;

    @NotBlank
    private String lastname;

    @Column(unique = true) // Questo invece genera un vincolo SUL DATABASE -> "2 customer non possono avere la stessa email"
    @Pattern(regexp = "[\\p{Alpha}\\d._%-]+@[\\p{Alpha}\\d._%-]+\\.[\\p{Alpha}]{2,4}", message = "customer's email wrong format") // Servono ad intercettare i dati prima di salvarli nel database
    private String email;

    @NotNull
    private LocalDate birthdate;

    @OneToOne(mappedBy = "customer")
    private Subscription subscription;

    @ManyToMany
    @JoinTable(
            name = "customers_courses",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
}
