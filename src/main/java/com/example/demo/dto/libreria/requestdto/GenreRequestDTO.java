package com.example.demo.dto.libreria.requestdto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreRequestDTO {

    // SERVE per POST e PUT
    private String name;
}
