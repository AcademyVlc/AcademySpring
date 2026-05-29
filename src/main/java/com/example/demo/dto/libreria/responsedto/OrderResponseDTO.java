package com.example.demo.dto.libreria.responsedto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private Integer id;

    private Integer qty;

    private Double totalPrice;
}
