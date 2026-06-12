package com.example.demo.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {

//    private Integer id;

    @NotBlank(message = "Firstname required")
    private String firstname;

    @NotBlank(message = "Lastname required")
    private String lastname;

    @NotBlank(message = "Email required")
    @Pattern(regexp = "[\\p{Alpha}\\d._%-]+@[\\p{Alpha}\\d._%-]+\\.[\\p{Alpha}]{2,4}", message = "customer's email wrong format")
    private String email;

    // La data deve essere nel passato o oggi
    @NotNull(message = "Birthdate required")
    @PastOrPresent
    private LocalDate birthdate;

}
