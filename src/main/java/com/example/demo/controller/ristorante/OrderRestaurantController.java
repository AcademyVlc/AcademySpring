package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.OrderRestaurantRequestDTO;
import com.example.demo.dto.ristorante.responsedto.OrderRestaurantResponseDTO;
import com.example.demo.services.ristorante.abstraction.OrderRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestaurantController {

    private final OrderRestaurantService orderRestaurantService;

    @GetMapping
    public ResponseEntity<List<OrderRestaurantResponseDTO>> findAll() {
        List<OrderRestaurantResponseDTO> orders = orderRestaurantService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRestaurantResponseDTO> findById(@PathVariable Integer id) {
        try {
            OrderRestaurantResponseDTO order = orderRestaurantService.findById(id);
            return ResponseEntity.ok(order);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<OrderRestaurantResponseDTO> save(@RequestBody OrderRestaurantRequestDTO orderRestaurantRequestDTO) {
        OrderRestaurantResponseDTO order = orderRestaurantService.save(orderRestaurantRequestDTO);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderRestaurantResponseDTO> update(@PathVariable Integer id, @RequestBody OrderRestaurantRequestDTO orderRestaurantRequestDTO) {
        OrderRestaurantResponseDTO order = orderRestaurantService.update(id, orderRestaurantRequestDTO);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        orderRestaurantService.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
