package com.example.demo.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RevenueDTO {
    private BigDecimal totalRevenue;
}
