package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.OrderRestaurantRequestDTO;
import com.example.demo.dto.ristorante.responsedto.OrderRestaurantResponseDTO;
import com.example.demo.services.ristorante.abstraction.OrderRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestaurantController {

    private final OrderRestaurantService orderRestaurantService;

    @GetMapping
    public List<OrderRestaurantResponseDTO> findAll() {
        return orderRestaurantService.findAll();
    }

    @GetMapping("/{id}")
    public OrderRestaurantResponseDTO findById(@PathVariable Integer id) {
        return orderRestaurantService.findById(id);
    }

    @PostMapping
    public OrderRestaurantResponseDTO save(@RequestBody OrderRestaurantRequestDTO orderRestaurantRequestDTO) {
        return orderRestaurantService.save(orderRestaurantRequestDTO);
    }

    @PutMapping("/{id}")
    public OrderRestaurantResponseDTO update(@PathVariable Integer id, @RequestBody OrderRestaurantRequestDTO orderRestaurantRequestDTO) {
        return orderRestaurantService.update(id, orderRestaurantRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        return orderRestaurantService.deletedById(id);
    }
}
