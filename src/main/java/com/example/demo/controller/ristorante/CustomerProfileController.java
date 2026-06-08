package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.CustomerProfileRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerProfileResponseDTO;
import com.example.demo.services.ristorante.abstraction.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers-profile")
@RequiredArgsConstructor
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    @GetMapping
    public ResponseEntity<List<CustomerProfileResponseDTO>> findAll() {
        List<CustomerProfileResponseDTO> customerProfiles = customerProfileService.findAll();
        return ResponseEntity.ok(customerProfiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfileResponseDTO> findById(@PathVariable Integer id) {
        CustomerProfileResponseDTO customer = customerProfileService.findById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerProfileResponseDTO> save(@RequestBody CustomerProfileRequestDTO customerProfileRequestDTO) {
        CustomerProfileResponseDTO customer = customerProfileService.save(customerProfileRequestDTO);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerProfileResponseDTO> update(@PathVariable Integer id, @RequestBody CustomerProfileRequestDTO customerProfileRequestDTO) {
        CustomerProfileResponseDTO customer = customerProfileService.update(id, customerProfileRequestDTO);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        customerProfileService.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
