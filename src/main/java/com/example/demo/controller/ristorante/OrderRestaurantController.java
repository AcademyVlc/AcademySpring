package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.OrderRestaurantRequestDTO;
import com.example.demo.dto.ristorante.responsedto.OrderRestaurantResponseDTO;
import com.example.demo.services.ristorante.abstraction.OrderRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestaurantController {

    private final OrderRestaurantService orderRestaurantService;

    @GetMapping
    public ResponseEntity<List<OrderRestaurantResponseDTO>> findAll() {
        List<OrderRestaurantResponseDTO> dishes = orderRestaurantService.findAll();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRestaurantResponseDTO> findById(@PathVariable Integer id) {
        OrderRestaurantResponseDTO dish = orderRestaurantService.findById(id);
        return ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<OrderRestaurantResponseDTO> save(@RequestBody OrderRestaurantRequestDTO orderRestaurantRequestDTO) {
        OrderRestaurantResponseDTO dish = orderRestaurantService.save(orderRestaurantRequestDTO);
        return ResponseEntity.ok(dish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderRestaurantResponseDTO> update(@PathVariable Integer id, @RequestBody OrderRestaurantRequestDTO orderRestaurantRequestDTO) {
        OrderRestaurantResponseDTO dish = orderRestaurantService.update(id, orderRestaurantRequestDTO);
        return ResponseEntity.ok(dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        orderRestaurantService.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
