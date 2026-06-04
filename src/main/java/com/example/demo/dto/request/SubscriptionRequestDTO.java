package com.example.demo.dto.request;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionRequestDTO {

//    private Integer id;

    private String type;

//    private LocalDate startDate;
//
//    private LocalDate endDate;

    private BigDecimal price;
}
