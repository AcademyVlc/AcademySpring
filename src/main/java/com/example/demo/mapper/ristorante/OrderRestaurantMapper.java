package com.example.demo.mapper.ristorante;

import com.example.demo.dto.ristorante.requestdto.OrderRestaurantRequestDTO;
import com.example.demo.dto.ristorante.responsedto.OrderRestaurantResponseDTO;
import com.example.demo.entity.ristorante.OrderRestaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderRestaurantMapper {

    @Mapping(target = "customerId", source = "customer.id" )
    OrderRestaurantResponseDTO entityToResponseDTO(OrderRestaurant orderRestaurant);
    List<OrderRestaurantResponseDTO> entityToResponseDTO(List<OrderRestaurant> orderRestaurant);

    @Mapping(target = "customer.id", source = "customerId" )
    OrderRestaurant requestDTOToEntity(OrderRestaurantRequestDTO orderRestaurantRequestDTO);
}
