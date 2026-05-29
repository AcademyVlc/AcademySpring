package com.example.demo.controller.libreria;

import com.example.demo.dto.libreria.AuthorDTO;
import com.example.demo.dto.libreria.requestdto.AuthorRequestDTO;
import com.example.demo.dto.libreria.responsedto.AuthorResponseDTO;
import com.example.demo.services.libreria.abstraction.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
// Nuova sintassi per fare l'Autowired. Quando la classe viene istanziata carica automaticamente la costante.
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorResponseDTO> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public AuthorResponseDTO findById(@PathVariable Integer id) {
        return authorService.findById(id);
    }

    @PostMapping
    public AuthorResponseDTO save(@RequestBody AuthorRequestDTO authorRequestDTO) {
        // Ignora qualsiasi ID arrivi dal client --> Crea sempre un nuovo record.
        return authorService.save(authorRequestDTO);
    }

    @PutMapping("/{id}")
    public AuthorResponseDTO update(@PathVariable Integer id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        return authorService.save(authorRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        authorService.deletedById(id);
        return "Deleted author with id - " + id;
    }


}
