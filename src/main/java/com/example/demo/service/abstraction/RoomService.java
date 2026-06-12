package com.example.demo.service.abstraction;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.request.RoomRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.dto.response.RoomResponseDTO;
import com.example.demo.entity.palestra.Customer;
import com.example.demo.entity.palestra.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<RoomResponseDTO> findAll();

    RoomResponseDTO findById(Integer id);

    RoomResponseDTO save(RoomRequestDTO roomRequestDTO);

    RoomResponseDTO update(Integer id, RoomRequestDTO roomRequestDTO);

    String deletedById(Integer id);

    RoomResponseDTO findByName(String roomName);
}
