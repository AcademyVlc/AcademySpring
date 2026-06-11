package com.example.demo.service.implementation;

import com.example.demo.dto.request.SubscriptionRequestDTO;
import com.example.demo.dto.response.RevenueDTO;
import com.example.demo.dto.response.SubscriptionResponseDTO;
import com.example.demo.entity.palestra.Subscription;
import com.example.demo.exception_handling.palestra.exceptions.SubscriptionNotFoundException;
import com.example.demo.mapper.palestra.SubscriptionMapper;
import com.example.demo.repository.palestra.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private SubscriptionMapper subscriptionMapper;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Test
    void findAll() {
        List<Subscription> subscriptionList = new ArrayList<>();

        SubscriptionResponseDTO subscriptionResponseDTO = new SubscriptionResponseDTO();
        subscriptionResponseDTO.setId(1);

        List<SubscriptionResponseDTO> subscriptionResponseDTOS = new ArrayList<>();
        subscriptionResponseDTOS.add(subscriptionResponseDTO);

        when(subscriptionRepository.findAll()).thenReturn(subscriptionList);
        when(subscriptionMapper.entityToResponseDTO(subscriptionList)).thenReturn(subscriptionResponseDTOS);

        List<SubscriptionResponseDTO> result = subscriptionService.findAll();

        assertEquals(1, result.get(0).getId());

        verify(subscriptionRepository).findAll();
        verify(subscriptionMapper).entityToResponseDTO(subscriptionList);
    }

    @Test
    void findById() {
        Integer id = 1;
        Subscription subscription = new Subscription();

        SubscriptionResponseDTO subscriptionResponseDTO = new SubscriptionResponseDTO();
        subscriptionResponseDTO.setId(1);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));
        when(subscriptionMapper.entityToResponseDTO(subscription)).thenReturn(subscriptionResponseDTO);

        SubscriptionResponseDTO result = subscriptionService.findById(id);

        assertEquals(1, result.getId());

        verify(subscriptionRepository).findById(id);
        verify(subscriptionMapper).entityToResponseDTO(subscription);
    }

    @Test
    void findByIdNotFound() {
        Integer id = 1;

        when(subscriptionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(SubscriptionNotFoundException.class, () -> subscriptionService.findById(id));
    }

    @Test
    void save() {
        SubscriptionRequestDTO subscriptionRequestDTO = new SubscriptionRequestDTO();
        subscriptionRequestDTO.setPrice(BigDecimal.valueOf(100));
        subscriptionRequestDTO.setType("monthly");

        SubscriptionResponseDTO subscriptionResponseDTO = new SubscriptionResponseDTO();
        subscriptionResponseDTO.setPrice(BigDecimal.valueOf(100));
        subscriptionResponseDTO.setType("monthly");

        Subscription subscription = new Subscription();
        Subscription savedSubscription = new Subscription();

        when(subscriptionMapper.requestDTOToEntity(subscriptionRequestDTO)).thenReturn(subscription);
        when(subscriptionRepository.save(subscription)).thenReturn(savedSubscription);
        when(subscriptionMapper.entityToResponseDTO(savedSubscription)).thenReturn(subscriptionResponseDTO);

        SubscriptionResponseDTO result = subscriptionService.save(subscriptionRequestDTO);

        assertEquals(BigDecimal.valueOf(100), result.getPrice());
        assertEquals("monthly", result.getType());

        verify(subscriptionMapper).requestDTOToEntity(subscriptionRequestDTO);
        verify(subscriptionRepository).save(subscription);
        verify(subscriptionMapper).entityToResponseDTO(savedSubscription);
    }

    @Test
    void update() {
        Integer id = 1;
        SubscriptionRequestDTO subscriptionRequestDTO = new SubscriptionRequestDTO();
        subscriptionRequestDTO.setPrice(BigDecimal.valueOf(100));
        subscriptionRequestDTO.setType("monthly");

        SubscriptionResponseDTO subscriptionResponseDTO = new SubscriptionResponseDTO();
        subscriptionResponseDTO.setPrice(BigDecimal.valueOf(100));
        subscriptionResponseDTO.setType("monthly");

        Subscription subscription = new Subscription();
        Subscription savedSubscription = new Subscription();

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));
        when(subscriptionRepository.save(subscription)).thenReturn(savedSubscription);
        when(subscriptionMapper.entityToResponseDTO(savedSubscription)).thenReturn(subscriptionResponseDTO);

        SubscriptionResponseDTO result = subscriptionService.update(id, subscriptionRequestDTO);

        assertEquals(BigDecimal.valueOf(100), result.getPrice());
        assertEquals("monthly", result.getType());

        verify(subscriptionRepository).findById(id);
        verify(subscriptionRepository).save(subscription);
        verify(subscriptionMapper).entityToResponseDTO(savedSubscription);
    }

    @Test
    void deletedById() {
        Integer id = 1;

        when(subscriptionRepository.existsById(id)).thenReturn(true);
        doNothing().when(subscriptionRepository).deleteById(id);

        String result = subscriptionService.deletedById(id);

        assertEquals("Deleted subscription with id - 1", result);
        verify(subscriptionRepository).deleteById(id);
    }

    @Test
    void calculateActiveSubscriptionsRevenue() {
        RevenueDTO revenueDTO = new RevenueDTO();
        revenueDTO.setTotalRevenue(BigDecimal.valueOf(100));
        BigDecimal totalRevenue = revenueDTO.getTotalRevenue();

        when(subscriptionRepository.calculateActiveSubscriptionsRevenue()).thenReturn(totalRevenue);

        RevenueDTO result = subscriptionService.calculateActiveSubscriptionsRevenue();

        assertEquals(totalRevenue, result.getTotalRevenue());

        verify(subscriptionRepository).calculateActiveSubscriptionsRevenue();
    }
}