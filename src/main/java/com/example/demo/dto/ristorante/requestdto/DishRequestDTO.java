package com.example.demo.dto.ristorante.requestdto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishRequestDTO {

     private String name;

    private Double price;

    private boolean available;

    private String categoryName;

    private String chefName;
}
