package com.example.demo.service.implementation;

import com.example.demo.dto.request.RoomRequestDTO;
import com.example.demo.dto.response.RoomResponseDTO;
import com.example.demo.entity.palestra.Room;
import com.example.demo.exception_handling.palestra.exceptions.RoomNotFoundException;
import com.example.demo.mapper.palestra.RoomMapper;
import com.example.demo.repository.palestra.RoomRepository;
import com.example.demo.service.abstraction.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public List<RoomResponseDTO> findAll() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.entityToResponseDTO(rooms);
    }

    @Override
    public RoomResponseDTO findById(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not founded"));
        return roomMapper.entityToResponseDTO(room);
    }

    @Override
    public RoomResponseDTO save(RoomRequestDTO roomRequestDTO) {
        Room room = roomMapper.requestDTOToEntity(roomRequestDTO);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.entityToResponseDTO(savedRoom);
    }

    @Override
    public RoomResponseDTO update(Integer id, RoomRequestDTO roomRequestDTO) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Room not founded"));

        room.setName(roomRequestDTO.getName());
        room.setCapacity(roomRequestDTO.getCapacity());

        Room savedRoom = roomRepository.save(room);

        return roomMapper.entityToResponseDTO(savedRoom);
    }

    @Override
    public String deletedById(Integer id) {
        if (!roomRepository.existsById(id)) {
            throw new RoomNotFoundException("Room not founded");
        }

        roomRepository.deleteById(id);
        return "Deleted room with id - " + id;
    }
}
