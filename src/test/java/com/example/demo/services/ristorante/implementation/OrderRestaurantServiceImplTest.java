package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.OrderRestaurantRequestDTO;
import com.example.demo.dto.ristorante.responsedto.OrderRestaurantResponseDTO;
import com.example.demo.entity.ristorante.OrderRestaurant;
import com.example.demo.mapper.ristorante.OrderRestaurantMapper;
import com.example.demo.repository.ristorante.OrderRestaurantRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderRestaurantServiceImplTest {

    @Mock
    private OrderRestaurantRepository orderRestaurantRepository;

    @Mock
    private OrderRestaurantMapper orderRestaurantMapper;

    @InjectMocks
    private OrderRestaurantServiceImpl orderRestaurantService;

    @Test
    void findAll() {
        List<OrderRestaurant> orderRestaurants = new ArrayList<>();
        orderRestaurants.add(new OrderRestaurant());

        List<OrderRestaurantResponseDTO> orderRestaurantResponseDTOS = new ArrayList<>();
        OrderRestaurantResponseDTO orderRestaurantResponseDTO = new OrderRestaurantResponseDTO();
        orderRestaurantResponseDTO.setId(1);
        orderRestaurantResponseDTOS.add(orderRestaurantResponseDTO);

        when(orderRestaurantRepository.findAll()).thenReturn(orderRestaurants);
        when(orderRestaurantMapper.entityToResponseDTO(orderRestaurants)).thenReturn(orderRestaurantResponseDTOS);

        List<OrderRestaurantResponseDTO> result = orderRestaurantService.findAll();
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void findById() {
        Integer id = 1;
        OrderRestaurant orderRestaurant = new OrderRestaurant();

        OrderRestaurantResponseDTO orderRestaurantResponseDTO = new OrderRestaurantResponseDTO();
        orderRestaurantResponseDTO.setId(1);

        when(orderRestaurantRepository.findById(id)).thenReturn(Optional.of(orderRestaurant));
        when(orderRestaurantMapper.entityToResponseDTO(orderRestaurant)).thenReturn(orderRestaurantResponseDTO);

        OrderRestaurantResponseDTO result = orderRestaurantService.findById(id);

        assertEquals(1, result.getId());
    }

    @Test
    void save() {

        OrderRestaurantRequestDTO orderRestaurantRequestDTO = new OrderRestaurantRequestDTO();
        orderRestaurantRequestDTO.setCustomerId(1);

        OrderRestaurant orderRestaurant = new OrderRestaurant();
        OrderRestaurant savedOrderRestaurant = new OrderRestaurant();

        OrderRestaurantResponseDTO orderRestaurantResponseDTO = new OrderRestaurantResponseDTO();
        orderRestaurantResponseDTO.setCustomerId(1);

        when(orderRestaurantMapper.requestDTOToEntity(orderRestaurantRequestDTO)).thenReturn(orderRestaurant);
        when(orderRestaurantRepository.save(orderRestaurant)).thenReturn(savedOrderRestaurant);
        when(orderRestaurantMapper.entityToResponseDTO(savedOrderRestaurant)).thenReturn(orderRestaurantResponseDTO);

        OrderRestaurantResponseDTO result = orderRestaurantService.save(orderRestaurantRequestDTO);

        assertEquals(1, result.getCustomerId());
    }

    @Test
    void update() {
        Integer id = 1;

        OrderRestaurantRequestDTO orderRestaurantRequestDTO = new OrderRestaurantRequestDTO();

        orderRestaurantRequestDTO.setCustomerId(1);
        orderRestaurantRequestDTO.setTotalPrice(BigDecimal.valueOf(50));
        orderRestaurantRequestDTO.setQtyProducts(3);

        OrderRestaurant orderRestaurant = new OrderRestaurant();

        OrderRestaurant updatedOrderRestaurant = new OrderRestaurant();

        OrderRestaurantResponseDTO orderRestaurantResponseDTO = new OrderRestaurantResponseDTO();

        orderRestaurantResponseDTO.setCustomerId(1);
        orderRestaurantResponseDTO.setTotalPrice(BigDecimal.valueOf(50));
        orderRestaurantResponseDTO.setQtyProducts(3);

        when(orderRestaurantMapper.requestDTOToEntity(orderRestaurantRequestDTO)).thenReturn(orderRestaurant);

        when(orderRestaurantRepository.save(orderRestaurant)).thenReturn(updatedOrderRestaurant);

        when(orderRestaurantMapper.entityToResponseDTO(updatedOrderRestaurant)).thenReturn(orderRestaurantResponseDTO);

        OrderRestaurantResponseDTO result = orderRestaurantService.update(id, orderRestaurantRequestDTO);

        assertEquals(1, result.getCustomerId());
        assertEquals(BigDecimal.valueOf(50), result.getTotalPrice());
        assertEquals(3, result.getQtyProducts());
    }

    @Test
    void deletedById() {

        doNothing().when(orderRestaurantRepository).deleteById(anyInt());

        when(orderRestaurantRepository.existsById(anyInt())).thenReturn(true);
        orderRestaurantService.deletedById(anyInt());

        when(orderRestaurantRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> orderRestaurantService.deletedById(anyInt()));
    }
}