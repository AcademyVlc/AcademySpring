package com.example.demo.service.implementation;

import com.example.demo.dto.request.RoomRequestDTO;
import com.example.demo.dto.response.RoomResponseDTO;
import com.example.demo.entity.palestra.Room;
import com.example.demo.exception_handling.palestra.exceptions.RoomNotFoundException;
import com.example.demo.mapper.palestra.RoomMapper;
import com.example.demo.repository.palestra.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomMapper roomMapper;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    void findAll() {
        List<Room> rooms = new ArrayList<>();

        List<RoomResponseDTO> roomResponseDTOS = new ArrayList<>();
        Room room = new Room();

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setName("giove");
        roomResponseDTOS.add(roomResponseDTO);

        when(roomRepository.findAll()).thenReturn(rooms);
        when(roomMapper.entityToResponseDTO(rooms)).thenReturn(roomResponseDTOS);

        List<RoomResponseDTO> result = roomService.findAll();

        assertEquals("giove", result.get(0).getName());

        verify(roomRepository).findAll();
        verify(roomMapper).entityToResponseDTO(rooms);
    }

    @Test
    void findById() {
        Integer id = 1;

        Room room = new Room();

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setName("giove");

        when(roomRepository.findById(id)).thenReturn(Optional.of(room));
        when(roomMapper.entityToResponseDTO(room)).thenReturn(roomResponseDTO);

        RoomResponseDTO result = roomService.findById(id);

        assertEquals("giove", result.getName());

        verify(roomRepository).findById(id);
        verify(roomMapper).entityToResponseDTO(room);
    }

    @Test
    void findByIdNotFound() {
        Integer id = 1;

        when(roomRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RoomNotFoundException.class, () -> roomService.findById(id));
    }

    @Test
    void save() {
        Room room = new Room();
        Room savedRoom = new Room();

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setName("giove");

        RoomRequestDTO roomRequestDTO = new RoomRequestDTO();
        roomRequestDTO.setName("giove");

        when(roomMapper.requestDTOToEntity(roomRequestDTO)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(savedRoom);
        when(roomMapper.entityToResponseDTO(savedRoom)).thenReturn(roomResponseDTO);

        RoomResponseDTO result = roomService.save(roomRequestDTO);

        assertEquals("giove", result.getName());

        verify(roomMapper).requestDTOToEntity(roomRequestDTO);
        verify(roomRepository).save(room);
        verify(roomMapper).entityToResponseDTO(savedRoom);
    }

    @Test
    void update() {
        Integer id = 1;

        Room room = new Room();
        Room updatedRoom = new Room();

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setName("giove");
        roomResponseDTO.setCapacity(100);

        RoomRequestDTO roomRequestDTO = new RoomRequestDTO();
        roomRequestDTO.setName("giove");
        roomRequestDTO.setCapacity(100);

        when(roomRepository.findById(id)).thenReturn(Optional.of(room));
        when(roomRepository.save(room)).thenReturn(updatedRoom);
        when(roomMapper.entityToResponseDTO(updatedRoom)).thenReturn(roomResponseDTO);

        RoomResponseDTO result = roomService.update(id, roomRequestDTO);

        assertEquals("giove", result.getName());
        assertEquals(100, result.getCapacity());

        verify(roomRepository).findById(id);
        verify(roomRepository).save(room);
        verify(roomMapper).entityToResponseDTO(updatedRoom);
    }

    @Test
    void deletedById() {
        Integer id = 1;

        when(roomRepository.existsById(id)).thenReturn(true);
        doNothing().when(roomRepository).deleteById(id);

        String result = roomService.deletedById(id);

        assertEquals("Deleted room with id - 1", result);
        verify(roomRepository).deleteById(id);
    }

    @Test
    void deletedByIdNotFound() {
        Integer id = 1;

        when(roomRepository.existsById(id)).thenReturn(false);

        assertThrows(RoomNotFoundException.class, () -> roomService.deletedById(id));
    }
}