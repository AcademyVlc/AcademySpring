package com.example.demo.dto.ristorante.responsedto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChefResponseDTO {

    private Integer id;

    private String name;

    private String specialization;
}
