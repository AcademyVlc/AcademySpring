package com.example.demo.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {

    private Integer id;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    private LocalDate birthdate;

}
