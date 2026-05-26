package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
// @NoArgsConstructor
// @AllArgsConstructor

public class BookRequestDTO {
    // Serve per i dati che il client ti manda in POST e PUT
    private String title;
    private String author;
    private String genre;
    private BigDecimal price;
    private boolean available;

}
