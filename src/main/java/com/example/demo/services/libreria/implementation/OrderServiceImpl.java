package com.example.demo.services.libreria.implementation;

import com.example.demo.dto.libreria.OrderDTO;
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
    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.entityToDTO(orders);
    }

    @Override
    public OrderDTO findById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not founded with id - " + id));
        return orderMapper.entityToDto(order);
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = orderMapper.dtoToEntity(orderDTO);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.entityToDto(savedOrder);
    }

    @Override
    public void deletedById(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not founded, with id - " + id);
        }
        orderRepository.deleteById(id);
    }
}
