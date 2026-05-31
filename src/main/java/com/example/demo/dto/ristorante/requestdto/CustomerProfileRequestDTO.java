package com.example.demo.dto.ristorante.requestdto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerProfileRequestDTO {

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;



}
