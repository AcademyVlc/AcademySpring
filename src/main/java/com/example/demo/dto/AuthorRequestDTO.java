package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorRequestDTO {
    // Serve per i dati che il client ti manda in POST e PUT
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private LocalDate eliminationDate;
}
