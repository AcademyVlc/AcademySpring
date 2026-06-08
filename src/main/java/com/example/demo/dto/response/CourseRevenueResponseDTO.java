package com.example.demo.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRevenueResponseDTO {

    private String courseName;
    private Integer totalCustomer;
    private BigDecimal totalRevenue;
}
