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

    // Cerca piatti disponibili
    @GetMapping("/find-available-dishes")
    public List<DishResponseDTO> findAvailableDish() {
        return dishService.findAvailableDish();
    }

    // cerca piatti sotto un certo prezzo
    @GetMapping("/find-under-specific-price-dish")
    public List<DishResponseDTO> findUnderSpecificPriceDish(@RequestParam Double price) {
        return dishService.findUnderSpecificPriceDish(price);
    }

    // cerca piatti per categoria
    @GetMapping("/find-dish-by-category-name")
    public List<DishResponseDTO> findDishByCategoryName(@RequestParam String categoryName) {
        return dishService.findDishByCategoryName(categoryName);
    }

    // cerca piatti per chef
    @GetMapping("/find-dish-by-chef-name")
    public List<DishResponseDTO> findDishByChefName(@RequestParam String chefName) {
        return dishService.findDishByChefName(chefName);
    }

    // cerca piatti disponibili per categoria
    @GetMapping("/find-available-dish-by-category-name")
    public List<DishResponseDTO> findAvailableByCategoryName(@RequestParam String categoryName) {
        return dishService.findAvailableDishByCategoryName(categoryName);
    }

    // cerca piatti con prezzo compreso tra min e max
    @GetMapping("/find-dish-by-range-price")
    public List<DishResponseDTO> findDishByRangePrice(@RequestParam Double min, @RequestParam Double max) {
        return dishService.findDishByRangePrice(min, max);
    }

    // ricerca globale per nome piatto, chef o categoria
    @GetMapping("/global-search")
    public List<DishResponseDTO> globalSearch(@RequestParam String name){
        return dishService.globalSearch(name);
    }

    // conta piatti disponibili per categoria
    @GetMapping("/count-available-dish-group-by-category")
    public List<Object[]> countAvailableDishGroupByCategory(){
        return dishService.countDishGroupingByCategory();
    }
}
