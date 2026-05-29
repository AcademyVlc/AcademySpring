package com.example.demo.dto.libreria.requestdto;

import com.example.demo.entity.libreria.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorRequestDTO {
    // SERVE per POST e PUT

//    private Integer id; NON METTO ID
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
//    private LocalDate eliminationDate; // non metto elimination date
    private List<Book> books;
}
