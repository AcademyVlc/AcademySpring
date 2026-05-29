package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.ChefRequestDTO;
import com.example.demo.dto.ristorante.responsedto.ChefResponseDTO;
import com.example.demo.services.ristorante.abstraction.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/chefs")
public class ChefController {

    private final ChefService chefService;

    @GetMapping
    public List<ChefResponseDTO> findAll() {
        return chefService.findAll();
    }

    @GetMapping("/{id}")
    public ChefResponseDTO findById(@PathVariable Integer id) {
        return chefService.findById(id);
    }

    @PostMapping
    public ChefResponseDTO save(@RequestBody ChefRequestDTO chefRequestDTO) {
        return chefService.save(chefRequestDTO);
    }

    @PutMapping("/{id}")
    public ChefResponseDTO update(@PathVariable Integer id, @RequestBody ChefRequestDTO chefRequestDTO) {
        return chefService.save(chefRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletedById(@PathVariable Integer id) {
        chefService.deletedById(id);
    }
}
