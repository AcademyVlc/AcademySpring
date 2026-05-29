package com.example.demo.dto.ristorante.requestdto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDTO {

    private String name;
    private String email;
}
