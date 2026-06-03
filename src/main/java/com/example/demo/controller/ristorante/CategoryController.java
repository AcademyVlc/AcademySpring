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
        List<CategoryResponseDTO> categories = categoryService.findAll();
        return ResponseEntity.ok( categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Integer id) {
        CategoryResponseDTO category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO category = categoryService.save(categoryRequestDTO);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Integer id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO category = categoryService.update(id, categoryRequestDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        categoryService.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
