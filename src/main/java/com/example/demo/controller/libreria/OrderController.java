package com.example.demo.controller.libreria;

import com.example.demo.dto.libreria.OrderDTO;
import com.example.demo.dto.libreria.requestdto.OrderRequestDTO;
import com.example.demo.dto.libreria.responsedto.OrderResponseDTO;
import com.example.demo.services.libreria.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderResponseDTO> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO findById(@PathVariable Integer id){
        return orderService.findById(id);
    }

    @PostMapping
    public OrderResponseDTO save(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.save(orderRequestDTO);
    }

    @PutMapping("/{id}")
    public OrderResponseDTO update(@PathVariable Integer id, @RequestBody OrderRequestDTO orderRequestDTO){
        orderRequestDTO.setId(id);
        return orderService.save(orderRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String deletedById(@PathVariable Integer id){
        orderService.deletedById(id);
        return "Deleted order with id - " + id;
    }
}
