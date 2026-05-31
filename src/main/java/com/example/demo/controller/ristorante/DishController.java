package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.services.ristorante.abstraction.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public List<DishResponseDTO> findAll() {
        return dishService.findAll();
    }

    @GetMapping("/{id}")
    public DishResponseDTO findByID(@PathVariable Integer id) {
        return dishService.findById(id);
    }

    @PostMapping
    public DishResponseDTO save(@RequestBody DishRequestDTO dishRequestDTO) {
        return dishService.save(dishRequestDTO);
    }

    @PutMapping("/{id}")
    public DishResponseDTO update(@PathVariable Integer id, @RequestBody DishRequestDTO dishRequestDTO) {
        return dishService.update(id, dishRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletedByID(@PathVariable Integer id) {
        dishService.deletedById(id);
    }
}
