package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.CustomerRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerResponseDTO;
import com.example.demo.services.ristorante.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerResponseDTO> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO findById(@PathVariable Integer id) {
        return customerService.findById(id);
    }

    @PostMapping
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.save(customerRequestDTO);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO update(@PathVariable Integer id, @RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.update(id, customerRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletedById(@PathVariable Integer id) {
        customerService.deletedById(id);
    }
}
