package com.example.demo.dto.ristorante.responsedto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {

    private Integer id;
    private String name;
    private String email;

}
