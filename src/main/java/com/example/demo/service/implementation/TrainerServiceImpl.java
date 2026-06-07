package com.example.demo.service.implementation;

import com.example.demo.dto.request.TrainerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.TrainerResponseDTO;
import com.example.demo.entity.palestra.Course;
import com.example.demo.entity.palestra.Trainer;
import com.example.demo.mapper.palestra.CourseMapper;
import com.example.demo.mapper.palestra.TrainerMapper;
import com.example.demo.repository.palestra.CourseRepository;
import com.example.demo.repository.palestra.TrainerRepository;
import com.example.demo.service.abstraction.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private  final TrainerMapper trainerMapper;

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<TrainerResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public TrainerResponseDTO findById() {
        return null;
    }

    @Override
    public TrainerResponseDTO save(Trainer trainer) {
        return null;
    }

    @Override
    public TrainerResponseDTO update(Integer id, TrainerRequestDTO trainerRequestDTO) {
        return null;
    }

    @Override
    public String deletedById(Integer id) {
        return "";
    }

    @Override
    public List<CourseResponseDTO> seeTrainerCourses(Integer trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new NoSuchElementException("Trainer not found"));

        List<Course> courses = trainer.getCourses();
        return courseMapper.entityToResponseDTO(courses);
    }
}
