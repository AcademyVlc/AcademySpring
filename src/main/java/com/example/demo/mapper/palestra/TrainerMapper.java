package com.example.demo.mapper.palestra;

import com.example.demo.dto.request.TrainerRequestDTO;
import com.example.demo.dto.response.TrainerResponseDTO;
import com.example.demo.entity.palestra.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

    TrainerResponseDTO entityToResponseDTO(Trainer trainer);
    List<TrainerResponseDTO> entityToResponseDTO(List<Trainer> trainers);

    @Mapping(target = "id", ignore = true)
    Trainer requestDTOToEntity(TrainerRequestDTO trainerRequestDTO);
}
