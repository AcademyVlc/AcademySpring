package com.example.demo.dto.libreria.responsedto;

import com.example.demo.entity.libreria.Book;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponseDTO {
    // Serve per le risposte del backend
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private LocalDate eliminationDate;
//    private List<Book> books; --> Se lo metti vai in Stackoverflow
}
