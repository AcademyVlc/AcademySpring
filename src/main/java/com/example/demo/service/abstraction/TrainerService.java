package com.example.demo.service.abstraction;

import com.example.demo.dto.request.TrainerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.TrainerResponseDTO;
import com.example.demo.entity.palestra.Trainer;

import java.util.List;

public interface TrainerService {
    List<TrainerResponseDTO> findAll();
    TrainerResponseDTO findById();
    TrainerResponseDTO save(Trainer trainer);
    TrainerResponseDTO update(Integer id, TrainerRequestDTO trainerRequestDTO);
    String deletedById(Integer id);
    List<CourseResponseDTO> seeTrainerCourses(Integer trainerId);
}
