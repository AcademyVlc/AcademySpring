package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.CategoryRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CategoryResponseDTO;
import com.example.demo.services.ristorante.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    List<CategoryResponseDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO findById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public CategoryResponseDTO save(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.save(categoryRequestDTO);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO update(@PathVariable Integer id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.update(id ,categoryRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String deletedById(@PathVariable Integer id) {
        return categoryService.deletedById(id);
    }
}
