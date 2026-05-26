package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponseDTO {
    // Serve per i dati che io restituisco al Client
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private LocalDate eliminationDate;
}
