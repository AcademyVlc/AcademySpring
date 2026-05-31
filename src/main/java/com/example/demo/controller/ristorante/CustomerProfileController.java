package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.CustomerProfileRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerProfileResponseDTO;
import com.example.demo.services.ristorante.abstraction.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers-profile")
@RequiredArgsConstructor
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    @GetMapping
    public List<CustomerProfileResponseDTO> findAll() {
        return customerProfileService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerProfileResponseDTO findById(@PathVariable Integer id) {
        return customerProfileService.findById(id);
    }

    @PostMapping
    public CustomerProfileResponseDTO save(@RequestBody CustomerProfileRequestDTO customerProfileRequestDTO) {
        return customerProfileService.save(customerProfileRequestDTO);
    }

    @PutMapping("/{id}")
    public CustomerProfileResponseDTO update(@PathVariable Integer id, @RequestBody CustomerProfileRequestDTO customerProfileRequestDTO) {
        return customerProfileService.update(id, customerProfileRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletedById(@PathVariable Integer id) {
        customerProfileService.deletedById(id);
    }
}
