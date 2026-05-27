package com.example.demo.controller.libreria;

import com.example.demo.dto.libreria.GenreDTO;
import com.example.demo.services.libreria.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDTO> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public GenreDTO findById(@PathVariable Integer id) {
        return genreService.findById(id);
    }

    @PostMapping
    public GenreDTO save(@RequestBody GenreDTO genreDTO) {
        genreDTO.setId(null);
        return genreService.save(genreDTO);
    }

    @PutMapping("/{id}")
    public GenreDTO update(@PathVariable Integer id, @RequestBody GenreDTO genreDTO) {
        genreDTO.setId(id);
        return genreService.save(genreDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        genreService.deletedById(id);
        return "Deleted genre id: " + id;
    }

}
