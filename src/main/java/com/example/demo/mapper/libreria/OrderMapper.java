package com.example.demo.mapper.libreria;

import com.example.demo.dto.libreria.OrderDTO;
import com.example.demo.dto.libreria.requestdto.OrderRequestDTO;
import com.example.demo.dto.libreria.responsedto.OrderResponseDTO;
import com.example.demo.entity.libreria.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDTO entityToResponseDTO(Order order);
    List<OrderResponseDTO> entityToResponseDTO(List<Order> order);

    Order requestDTOToEntity(OrderRequestDTO orderRequestDTO);
}
