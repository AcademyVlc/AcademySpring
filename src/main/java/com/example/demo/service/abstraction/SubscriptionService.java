package com.example.demo.service.abstraction;

import com.example.demo.dto.request.SubscriptionRequestDTO;
import com.example.demo.dto.response.RevenueDTO;
import com.example.demo.dto.response.SubscriptionResponseDTO;
import com.example.demo.entity.palestra.Subscription;
import com.example.demo.service.abstraction.generic_service.GenericService;

import java.util.List;

public interface SubscriptionService extends GenericService<SubscriptionResponseDTO, SubscriptionRequestDTO, Integer> {
//    List<SubscriptionResponseDTO> findAll();
//    SubscriptionResponseDTO findById(Integer id);
//    SubscriptionResponseDTO save(SubscriptionRequestDTO subscriptionRequestDTO);
//    SubscriptionResponseDTO update(Integer id, SubscriptionRequestDTO subscriptionRequestDTO);
//    String deletedById(Integer id);
    // Calcolare incasso totale da abbonamenti attivi
    RevenueDTO calculateActiveSubscriptionsRevenue();
}
