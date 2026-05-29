package com.example.demo.dto.libreria.responsedto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreResponseDTO {

    // SERVE per leggere dal backend
    private Integer id;
    private String name;
}
