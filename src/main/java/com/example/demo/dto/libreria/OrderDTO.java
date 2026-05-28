package com.example.demo.dto.libreria;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Integer id;

    private String qty;

    private Double totalPrice;

}
