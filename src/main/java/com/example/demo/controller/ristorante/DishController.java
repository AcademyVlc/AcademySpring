package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CategoryCountDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseWrapperDTO;
import com.example.demo.services.ristorante.abstraction.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishResponseDTO>> findAll() {
        List<DishResponseDTO> dishes = dishService.findAll();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseWrapperDTO> findByID(@PathVariable Integer id) {
        try{
            DishResponseDTO byId = dishService.findById(id);
            byId.setEsito(true);
            return ResponseEntity.ok().body(byId);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            DishResponseDTO res = new DishResponseDTO();
            res.setEsito(false);
            res.setErrorMessages(List.of(e.getMessage()));
            res.setStatus(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(res.getStatus()).body(res);
        }

    }

    @PostMapping
    public ResponseEntity<DishResponseDTO> save(@RequestBody DishRequestDTO dishRequestDTO) {
        DishResponseDTO dish = dishService.save(dishRequestDTO);
        return ResponseEntity.ok(dish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishResponseDTO> update(@PathVariable Integer id, @RequestBody DishRequestDTO dishRequestDTO) {
        DishResponseDTO dish = dishService.update(id, dishRequestDTO);
        return ResponseEntity.ok(dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        dishService.deletedById(id);
        return ResponseEntity.noContent().build();
    }

    // Cerca piatti disponibili
    @GetMapping("/find-available-dishes")
    public ResponseEntity<List<DishResponseDTO>> findAvailableDish() {
        List<DishResponseDTO> dishes = dishService.findAvailableDish();
        return ResponseEntity.ok(dishes);
    }

    // cerca piatti sotto un certo prezzo
    @GetMapping("/find-under-specific-price-dish")
    public ResponseEntity<List<DishResponseDTO>> findUnderSpecificPriceDish(@RequestParam Double price) {
        List<DishResponseDTO> dishes = dishService.findUnderSpecificPriceDish(price);
        return ResponseEntity.ok(dishes);
    }

    // cerca piatti per categoria
    @GetMapping("/find-dish-by-category-name")
    public ResponseEntity<List<DishResponseDTO>> findDishByCategoryName(@RequestParam String categoryName) {
        List<DishResponseDTO> dishes = dishService.findDishByCategoryName(categoryName);
        return ResponseEntity.ok(dishes);
    }

    // cerca piatti per chef
    @GetMapping("/find-dish-by-chef-name")
    public ResponseEntity<List<DishResponseDTO>> findDishByChefName(@RequestParam String chefName) {
        List<DishResponseDTO> dishes = dishService.findDishByChefName(chefName);
        return ResponseEntity.ok(dishes);
    }

    // cerca piatti disponibili per categoria
    @GetMapping("/find-available-dish-by-category-name")
    public ResponseEntity<List<DishResponseDTO>> findAvailableByCategoryName(@RequestParam String categoryName) {
        List<DishResponseDTO> dishes = dishService.findAvailableDishByCategoryName(categoryName);
        return ResponseEntity.ok(dishes);
    }

    // cerca piatti con prezzo compreso tra min e max
    @GetMapping("/find-dish-by-range-price")
    public ResponseEntity<List<DishResponseDTO>> findDishByRangePrice(@RequestParam Double min, @RequestParam Double max) {
        List<DishResponseDTO> dishes = dishService.findDishByRangePrice(min, max);
        return ResponseEntity.ok(dishes);
    }

    // ricerca globale per nome piatto, chef o categoria
    @GetMapping("/global-search")
    public ResponseEntity<List<DishResponseDTO>> globalSearch(@RequestParam String name){
        List<DishResponseDTO> dtos = dishService.globalSearch(name);
        return ResponseEntity.ok().body(dtos);
    }

    // conta piatti disponibili per categoria
    @GetMapping("/count-available-dish-group-by-category")
    public List<CategoryCountDTO> countAvailableDishGroupByCategory(){
        return dishService.countDishGroupingByCategory();
    }
}
