package com.example.demo.repository.palestra;

import com.example.demo.entity.palestra.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    // Trovare trainer che tengono corsi in una certa sala
    @Query("""
        SELECT DISTINCT t
        FROM Trainer t
        JOIN t.courses c
        WHERE c.room.name = :roomName
        """)
    List<Trainer> findTrainersByRoomName(@Param("roomName") String roomName);

}
