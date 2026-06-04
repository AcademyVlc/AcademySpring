package com.example.demo.service.abstraction;

import com.example.demo.dto.request.SubscriptionRequestDTO;
import com.example.demo.dto.response.SubscriptionResponseDTO;
import com.example.demo.entity.palestra.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionResponseDTO> findAll();
    SubscriptionResponseDTO findById(Integer id);
    SubscriptionResponseDTO save(Subscription subscription);
    SubscriptionResponseDTO update(Integer id, SubscriptionRequestDTO subscriptionRequestDTO);
    String deletedById(Integer id);
}
