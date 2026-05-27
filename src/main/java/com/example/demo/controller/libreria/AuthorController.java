package com.example.demo.controller.libreria;

import com.example.demo.dto.libreria.AuthorDTO;
import com.example.demo.services.libreria.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor // Nuova sintassi per fare l'Autowired. Quando la classe viene istanziata carica automaticamente la costante.
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO findById(@PathVariable Integer id){
        return authorService.findById(id);
    }

    @PostMapping
    public AuthorDTO save(@RequestBody AuthorDTO authorDTO){
        authorDTO.setId(null);
        // Ignora qualsiasi ID arrivi dal client --> Crea sempre un nuovo record.
        return authorService.save(authorDTO);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable Integer id, @RequestBody AuthorDTO authorDTO){
        authorDTO.setId(id);
        return authorService.save(authorDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        authorService.deletedById(id);
        return "Deleted author with id - " + id;
    }



}
