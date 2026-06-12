package com.example.demo.repository.palestra;

import com.example.demo.entity.palestra.Room;
import com.example.demo.entity.palestra.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<Room> findByName(String roomName);

}
