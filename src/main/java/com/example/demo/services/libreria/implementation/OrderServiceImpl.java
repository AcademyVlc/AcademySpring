package com.example.demo.services.libreria.implementation;

import com.example.demo.dto.libreria.OrderDTO;
import com.example.demo.dto.libreria.requestdto.OrderRequestDTO;
import com.example.demo.dto.libreria.responsedto.OrderResponseDTO;
import com.example.demo.entity.libreria.Order;
import com.example.demo.mapper.libreria.OrderMapper;
import com.example.demo.repository.libreria.OrderRepository;
import com.example.demo.services.libreria.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponseDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.entityToResponseDTO(orders);
    }

    @Override
    public OrderResponseDTO findById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not founded with id - " + id));
        return orderMapper.entityToResponseDTO(order);
    }

    @Override
    public OrderResponseDTO save(OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.requestDTOToEntity(orderRequestDTO);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.entityToResponseDTO(savedOrder);
    }

    @Override
    public void deletedById(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not founded, with id - " + id);
        }
        orderRepository.deleteById(id);
    }
}
