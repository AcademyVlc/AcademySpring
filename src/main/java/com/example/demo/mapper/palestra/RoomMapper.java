package com.example.demo.mapper.palestra;

import com.example.demo.dto.request.RoomRequestDTO;
import com.example.demo.dto.response.RoomResponseDTO;
import com.example.demo.entity.palestra.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomResponseDTO entityToResponseDTO(Room room);
    List<RoomResponseDTO> entityToResponseDTO(List<Room> rooms);

    @Mapping(target = "id", ignore = true)
    Room requestDTOToEntity(RoomRequestDTO roomRequestDTOS);

}
