package com.example.demo.dto.ristorante.responsedto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerProfileResponseDTO {

    private Integer id;

    private String phone;

    private String address;

    private Integer customerId;
}
