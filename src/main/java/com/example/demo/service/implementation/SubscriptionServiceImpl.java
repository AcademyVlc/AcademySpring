package com.example.demo.service.implementation;

import com.example.demo.dto.request.SubscriptionRequestDTO;
import com.example.demo.dto.response.RevenueDTO;
import com.example.demo.dto.response.SubscriptionResponseDTO;
import com.example.demo.entity.palestra.Subscription;
import com.example.demo.mapper.palestra.SubscriptionMapper;
import com.example.demo.repository.palestra.SubscriptionRepository;
import com.example.demo.service.abstraction.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<SubscriptionResponseDTO> findAll() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptionMapper.entityToResponseDTO(subscriptions);
    }

    @Override
    public SubscriptionResponseDTO findById(Integer id) {
        return null;
    }

    @Override
    public SubscriptionResponseDTO save(SubscriptionRequestDTO subscriptionRequestDTO) {
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

    // Calcolare incasso totale da abbonamenti attivi
    @Override
    public RevenueDTO calculateActiveSubscriptionsRevenue() {
        BigDecimal total = subscriptionRepository.calculateActiveSubscriptionsRevenue();

        return RevenueDTO.builder()
                .totalRevenue(total)
                .build();
    }
}
