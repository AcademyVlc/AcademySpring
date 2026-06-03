package com.example.demo.dto.ristorante.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishResponseWrapperDTO {
    Boolean esito;
    List<String> errorMessages;
    HttpStatus status;
}
