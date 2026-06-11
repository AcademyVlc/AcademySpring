package com.example.demo.service.implementation;

import com.example.demo.dto.request.SubscriptionRequestDTO;
import com.example.demo.dto.response.RevenueDTO;
import com.example.demo.dto.response.SubscriptionResponseDTO;
import com.example.demo.entity.palestra.Subscription;
import com.example.demo.exception_handling.palestra.exceptions.SubscriptionNotFoundException;
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
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        return subscriptionMapper.entityToResponseDTO(subscription);
    }

    @Override
    public SubscriptionResponseDTO save(SubscriptionRequestDTO subscriptionRequestDTO) {
        Subscription subscription = subscriptionMapper.requestDTOToEntity(subscriptionRequestDTO);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.entityToResponseDTO(savedSubscription);
    }

    @Override
    public SubscriptionResponseDTO update(Integer id, SubscriptionRequestDTO subscriptionRequestDTO) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));

        subscription.setPrice(subscriptionRequestDTO.getPrice());
        subscription.setType(subscriptionRequestDTO.getType());

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        return subscriptionMapper.entityToResponseDTO(savedSubscription);
    }

    @Override
    public String deletedById(Integer id) {
        if (!subscriptionRepository.existsById(id)){
            throw new SubscriptionNotFoundException("Subscriptio not found");
        }
        subscriptionRepository.deleteById(id);
        return "Deleted subscription with id - " + id;
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
