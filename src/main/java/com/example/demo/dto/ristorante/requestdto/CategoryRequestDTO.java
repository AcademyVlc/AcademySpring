package com.example.demo.dto.ristorante.requestdto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDTO {

    private Integer id;
    private String name;
}
