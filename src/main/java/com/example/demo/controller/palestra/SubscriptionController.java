package com.example.demo.controller.palestra;

import com.example.demo.dto.response.RevenueDTO;
import com.example.demo.service.abstraction.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    // Calcolare incasso totale da abbonamenti attivi 
    @GetMapping("/active-revenue")
    public ResponseEntity<RevenueDTO> calculateActiveSubscriptionsRevenue() {
        RevenueDTO revenueDTO = subscriptionService.calculateActiveSubscriptionsRevenue();
        return ResponseEntity.ok(revenueDTO);
    }

}
