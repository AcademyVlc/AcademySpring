package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.ChefRequestDTO;
import com.example.demo.dto.ristorante.responsedto.ChefResponseDTO;
import com.example.demo.services.ristorante.abstraction.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/chefs")
public class ChefController {

    private final ChefService chefService;

    @GetMapping
    public ResponseEntity<List<ChefResponseDTO>> findAll() {
        List<ChefResponseDTO> chefs = chefService.findAll();
        return ResponseEntity.ok(chefs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChefResponseDTO> findById(@PathVariable Integer id) {
        ChefResponseDTO chef = chefService.findById(id);
        return ResponseEntity.ok(chef);
    }

    @PostMapping
    public ResponseEntity<ChefResponseDTO> save(@RequestBody ChefRequestDTO chefRequestDTO) {
        ChefResponseDTO chef = chefService.save(chefRequestDTO);
        return ResponseEntity.ok(chef);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChefResponseDTO> update(@PathVariable Integer id, @RequestBody ChefRequestDTO chefRequestDTO) {
        ChefResponseDTO chef = chefService.update(id, chefRequestDTO);
        return ResponseEntity.ok(chef);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        chefService.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
