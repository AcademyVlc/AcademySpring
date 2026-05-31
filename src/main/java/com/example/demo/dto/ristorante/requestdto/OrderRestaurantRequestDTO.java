package com.example.demo.dto.ristorante.requestdto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRestaurantRequestDTO {

    private Integer qtyProducts;

    private BigDecimal totalPrice;

    private Integer customerId;
}
