package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.CategoryRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CategoryResponseDTO;
import com.example.demo.services.ristorante.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> dishes = categoryService.findAll();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Integer id) {
        CategoryResponseDTO dish = categoryService.findById(id);
        return ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO dish = categoryService.save(categoryRequestDTO);
        return ResponseEntity.ok(dish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Integer id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO category = categoryService.update(id, categoryRequestDTO);
        return category;
    }

    @DeleteMapping("/{id}")
    public String deletedById(@PathVariable Integer id) {
        return categoryService.deletedById(id);
    }
}
