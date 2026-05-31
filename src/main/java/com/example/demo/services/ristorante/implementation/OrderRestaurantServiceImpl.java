package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.OrderRestaurantRequestDTO;
import com.example.demo.dto.ristorante.responsedto.OrderRestaurantResponseDTO;
import com.example.demo.entity.ristorante.OrderRestaurant;
import com.example.demo.mapper.ristorante.OrderRestaurantMapper;
import com.example.demo.repository.ristorante.OrderRestaurantRepository;
import com.example.demo.services.ristorante.abstraction.OrderRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderRestaurantServiceImpl implements OrderRestaurantService {

    private final OrderRestaurantRepository orderRestaurantRepository;
    private final OrderRestaurantMapper orderRestaurantMapper;

    @Override
    public List<OrderRestaurantResponseDTO> findAll() {
        List<OrderRestaurant> orderRestaurants = orderRestaurantRepository.findAll();
        return orderRestaurantMapper.entityToResponseDTO(orderRestaurants);
    }

    @Override
    public OrderRestaurantResponseDTO findById(Integer id) {
        OrderRestaurant orderRestaurant = orderRestaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Restaurant not founded"));
        return orderRestaurantMapper.entityToResponseDTO(orderRestaurant);
    }

    @Override
    public OrderRestaurantResponseDTO save(OrderRestaurantRequestDTO orderRestaurantRequestDTO) {
        OrderRestaurant orderRestaurant = orderRestaurantMapper.requestDTOToEntity(orderRestaurantRequestDTO);
        OrderRestaurant savedOrderRestaurant = orderRestaurantRepository.save(orderRestaurant);
        return orderRestaurantMapper.entityToResponseDTO(savedOrderRestaurant);
    }

    @Override
    public OrderRestaurantResponseDTO update(Integer id, OrderRestaurantRequestDTO orderRestaurantRequestDTO) {
        OrderRestaurant orderRestaurant = orderRestaurantMapper.requestDTOToEntity(orderRestaurantRequestDTO);

        orderRestaurant.setTotalPrice(orderRestaurantRequestDTO.getTotalPrice());
        orderRestaurant.setQtyProducts(orderRestaurantRequestDTO.getQtyProducts());

        OrderRestaurant updatedOrderRestaurant = orderRestaurantRepository.save(orderRestaurant);
        return orderRestaurantMapper.entityToResponseDTO(updatedOrderRestaurant);
    }

    @Override
    public String deletedById(Integer id) {
        if (!orderRestaurantRepository.existsById(id)){
            throw new RuntimeException("Order not founded");
        }

        orderRestaurantRepository.deleteById(id);
        return "Deleted order with id - " + id;
    }
}
