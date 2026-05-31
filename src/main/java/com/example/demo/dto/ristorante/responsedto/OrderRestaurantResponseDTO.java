package com.example.demo.dto.ristorante.responsedto;

import com.example.demo.entity.ristorante.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRestaurantResponseDTO {

    private Integer id;

    private Integer qtyProducts;

    private BigDecimal totalPrice;

    private Integer customerId;
}
