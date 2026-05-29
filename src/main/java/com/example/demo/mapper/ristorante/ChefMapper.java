package com.example.demo.mapper.ristorante;

import com.example.demo.dto.ristorante.requestdto.ChefRequestDTO;
import com.example.demo.dto.ristorante.responsedto.ChefResponseDTO;
import com.example.demo.entity.ristorante.Chef;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChefMapper {

    ChefResponseDTO entityToResponseDTO(Chef chef);

    List<ChefResponseDTO> entityToResponseDTO(List<Chef> chef);

    Chef requestDTOToEntity(ChefRequestDTO chefRequestDTO);
}
