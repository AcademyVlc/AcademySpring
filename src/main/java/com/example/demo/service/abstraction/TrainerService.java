package com.example.demo.service.abstraction;

import com.example.demo.dto.request.TrainerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.TrainerResponseDTO;
import com.example.demo.entity.palestra.Trainer;
import com.example.demo.service.abstraction.generic_service.GenericService;

import java.util.List;

public interface TrainerService extends GenericService<TrainerResponseDTO, TrainerRequestDTO, Integer> {
//    List<TrainerResponseDTO> findAll();
//
//    TrainerResponseDTO findById(Integer id);
//
//    TrainerResponseDTO save(TrainerRequestDTO trainerRequestDTO);
//
//    TrainerResponseDTO update(Integer id, TrainerRequestDTO trainerRequestDTO);
//
//    String deletedById(Integer id);

    TrainerResponseDTO findByFirstame(String trainerName);

    // Trova i corsi che fa un trainer
    List<CourseResponseDTO> seeTrainerCourses(Integer trainerId);

    // Cambia trainer a un corso
    CourseResponseDTO changeTrainerCourse(Integer trainerId, Integer courseId);

    // Trovare trainer che tengono corsi in una certa sala
    List<TrainerResponseDTO> findTrainersByRoomName(String roomName);
}
