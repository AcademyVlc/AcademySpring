package com.example.demo.mapper.libreria;

import com.example.demo.dto.libreria.OrderDTO;
import com.example.demo.entity.libreria.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "qty", source = "qty")
    @Mapping(target = "totalPrice", source = "totalPrice")
    OrderDTO entityToDto(Order order);

    Order dtoToEntity(OrderDTO orderDTO);

    List<OrderDTO> entityToDTO(List<Order> orders);

    List<Order> dtoToEntity(List<OrderDTO> ordersDTO);
}
