package com.example.demo.controller.libreria;

import com.example.demo.dto.libreria.OrderDTO;
import com.example.demo.services.libreria.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDTO> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable Integer id){
        return orderService.findById(id);
    }

    @PostMapping
    public OrderDTO save(@RequestBody OrderDTO orderDTO){
        orderDTO.setId(null);
        return orderService.save(orderDTO);
    }

    @PutMapping("/{id}")
    public OrderDTO update(@PathVariable Integer id, @RequestBody OrderDTO orderDTO){
        orderDTO.setId(id);
        return orderService.save(orderDTO);
    }

    @DeleteMapping("/{id}")
    public String deletedById(@PathVariable Integer id){
        orderService.deletedById(id);
        return "Deleted order with id - " + id;
    }
}
