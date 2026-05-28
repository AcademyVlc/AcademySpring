package com.example.demo.dto.libreria;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDTO {
    // SERVE per POST e PUT
    // Non metto l'id
    private String title;
    private Integer authorId;
    private Integer genreId;
    private BigDecimal price;
    private boolean available;

    /**
     * Il client manda: 
     * {
     *   "title": "It",
     *   "authorId": 1,
     *   "genreId": 2,
     *   "price": 19.99,
     *   "available": true
     * }
     * che è corretto perché:
     * authorId → FK
     * genreId → FK
     */
}
