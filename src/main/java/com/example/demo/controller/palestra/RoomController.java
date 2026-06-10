package com.example.demo.controller.palestra;

import com.example.demo.dto.request.RoomRequestDTO;
import com.example.demo.dto.response.RoomResponseDTO;
import com.example.demo.service.implementation.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServiceImpl roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> findAll(){
        List<RoomResponseDTO> rooms = roomService.findAll();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> findById(@PathVariable Integer id){
        RoomResponseDTO room = roomService.findById(id);
        room.setSuccess(true);
        return ResponseEntity.ok(room);
    }

    @PostMapping
    public ResponseEntity<RoomResponseDTO> save(@RequestBody RoomRequestDTO roomRequestDTO){
        RoomResponseDTO savedRoom = roomService.save(roomRequestDTO);
        return ResponseEntity.ok(savedRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> update(@PathVariable Integer id, @RequestBody RoomRequestDTO roomRequestDTO){
        RoomResponseDTO updatedRoom = roomService.update(id, roomRequestDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id){
        roomService.deletedById(id);
        return ResponseEntity.noContent().build();
    }
}
