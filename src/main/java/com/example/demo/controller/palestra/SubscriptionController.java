package com.example.demo.controller.palestra;

import com.example.demo.dto.request.SubscriptionRequestDTO;
import com.example.demo.dto.response.RevenueDTO;
import com.example.demo.dto.response.SubscriptionResponseDTO;
import com.example.demo.service.abstraction.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDTO>> findAll() {
        List<SubscriptionResponseDTO> subscriptions = subscriptionService.findAll();
        return ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDTO> findById(@PathVariable Integer id) {
        SubscriptionResponseDTO subscription = subscriptionService.findById(id);
        subscription.setSuccess(true);
        return ResponseEntity.ok(subscription);
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> save(@RequestBody SubscriptionRequestDTO subscriptionRequestDTO) {
        SubscriptionResponseDTO savedSubscription = subscriptionService.save(subscriptionRequestDTO);
        return ResponseEntity.ok(savedSubscription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDTO> update(@PathVariable Integer id, @RequestBody SubscriptionRequestDTO subscriptionRequestDTO) {
        SubscriptionResponseDTO updatedSubscription = subscriptionService.update(id, subscriptionRequestDTO);
        return ResponseEntity.ok(updatedSubscription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id) {
        subscriptionService.deletedById(id);
        return ResponseEntity.noContent().build();
    }

    // Calcolare incasso totale da abbonamenti attivi
    @GetMapping("/active-revenue")
    public ResponseEntity<RevenueDTO> calculateActiveSubscriptionsRevenue() {
        RevenueDTO revenueDTO = subscriptionService.calculateActiveSubscriptionsRevenue();
        return ResponseEntity.ok(revenueDTO);
    }

}
