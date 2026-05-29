package com.example.demo.controller.libreria;

import com.example.demo.dto.libreria.GenreDTO;
import com.example.demo.dto.libreria.requestdto.GenreRequestDTO;
import com.example.demo.dto.libreria.responsedto.GenreResponseDTO;
import com.example.demo.mapper.libreria.GenreMapper;
import com.example.demo.services.libreria.abstraction.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;


    @GetMapping
    public List<GenreResponseDTO> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public GenreResponseDTO findById(@PathVariable Integer id) {
        return genreService.findById(id);
    }

    @PostMapping
    public GenreResponseDTO save(@RequestBody GenreRequestDTO genreRequestDTO) {
        return genreService.save(genreRequestDTO);
    }

    @PutMapping("/{id}")
    public GenreResponseDTO update(@PathVariable Integer id, @RequestBody GenreRequestDTO genreRequestDTO) {
        return genreService.save(genreRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        genreService.deletedById(id);
        return "Deleted genre id: " + id;
    }

}
