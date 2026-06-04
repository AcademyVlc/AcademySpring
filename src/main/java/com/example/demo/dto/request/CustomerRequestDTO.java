package com.example.demo.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {

//    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private LocalDate birthdate;

}
