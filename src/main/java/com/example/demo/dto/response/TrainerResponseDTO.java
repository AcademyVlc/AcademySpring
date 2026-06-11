package com.example.demo.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerResponseDTO extends ErrorResponseDTO{

    private Integer id;

    private String firstname;

    private String lastname;

    private String specialization;
}
