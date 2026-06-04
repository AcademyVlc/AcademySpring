package com.example.demo.service.implementation;

import com.example.demo.dto.request.SubscriptionRequestDTO;
import com.example.demo.dto.response.SubscriptionResponseDTO;
import com.example.demo.entity.palestra.Subscription;
import com.example.demo.service.abstraction.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public List<SubscriptionResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public SubscriptionResponseDTO findById(Integer id) {
        return null;
    }

    @Override
    public SubscriptionResponseDTO save(Subscription subscription) {
        return null;
    }

    @Override
    public SubscriptionResponseDTO update(Integer id, SubscriptionRequestDTO subscriptionRequestDTO) {
        return null;
    }

    @Override
    public String deletedById(Integer id) {
        return "";
    }
}
