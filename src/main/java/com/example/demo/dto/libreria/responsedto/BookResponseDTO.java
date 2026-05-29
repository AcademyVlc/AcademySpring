package com.example.demo.dto.libreria.responsedto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDTO {
    // Serve per le risposte del backend
    private Integer id;
    private String title;
    private String authorName;
    private String genreName;
    private BigDecimal price;
    private boolean available;

    /**
     * Il backend restituisce:
     * {
     *   "id": 10,
     *   "title": "It",
     *   "authorName": "Stephen King",
     *   "genreName": "Horror",
     *   "price": 19.99,
     *   "available": true
     * }
     * che è molto più leggibile.
     */
}
