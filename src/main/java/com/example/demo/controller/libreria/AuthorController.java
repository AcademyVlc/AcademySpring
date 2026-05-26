package com.example.demo.controller.libreria;

import com.example.demo.entity.libreria.Author;
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
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Integer id){
        return authorService.findById(id);
    }

    @PostMapping
    public Author save(@RequestBody Author author){
        author.setId(null);
        // Ignora qualsiasi ID arrivi dal client --> Crea sempre un nuovo record.
        return authorService.save(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Integer id, @RequestBody Author author){
        author.setId(id);
        return authorService.save(author);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        authorService.deletedById(id);
        return "Deleted author with id - " + id;
    }



}
