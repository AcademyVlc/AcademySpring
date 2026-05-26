package com.example.demo.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDTO {
    // Serve per i dati che io restituisco al Client
    private Integer id;
    private String title;
    private String author;
    private String genre;
    private BigDecimal price;
    private boolean available;

}
