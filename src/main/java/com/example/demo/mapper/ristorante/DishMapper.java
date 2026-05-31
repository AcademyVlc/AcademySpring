package com.example.demo.mapper.ristorante;

import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.entity.ristorante.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "chefName", source = "chef.name")
    DishResponseDTO entityToResponseDTO(Dish dish);
    List<DishResponseDTO> entityToResponseDTO(List<Dish> dish);

    @Mapping(target = "category.name", source = "categoryName")
    @Mapping(target = "chef.name", source = "chefName")
    Dish requestDTOToEntity(DishRequestDTO dishRequestDTO);
}
