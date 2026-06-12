package com.example.demo.service.implementation;

import com.example.demo.dto.request.TrainerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.TrainerResponseDTO;
import com.example.demo.entity.palestra.Course;
import com.example.demo.entity.palestra.Trainer;
import com.example.demo.exception_handling.palestra.exceptions.TrainerNotFoundException;
import com.example.demo.mapper.palestra.CourseMapper;
import com.example.demo.mapper.palestra.TrainerMapper;
import com.example.demo.repository.palestra.CourseRepository;
import com.example.demo.repository.palestra.TrainerRepository;
import com.example.demo.service.abstraction.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<TrainerResponseDTO> findAll() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainerMapper.entityToResponseDTO(trainers);
    }

    @Override
    public TrainerResponseDTO findById(Integer id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Trainer not found"));
        return trainerMapper.entityToResponseDTO(trainer);
    }

    @Override
    public TrainerResponseDTO save(TrainerRequestDTO trainerRequestDTO) {
        Trainer trainer = trainerMapper.requestDTOToEntity(trainerRequestDTO);
        Trainer savedTrainer = trainerRepository.save(trainer);
        return trainerMapper.entityToResponseDTO(savedTrainer);
    }

    @Override
    public TrainerResponseDTO update(Integer id, TrainerRequestDTO trainerRequestDTO) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Trainer not found"));
        trainer.setFirstname(trainerRequestDTO.getFirstname());
        trainer.setLastname(trainerRequestDTO.getLastname());
        trainer.setSpecialization(trainerRequestDTO.getSpecialization());

        Trainer savedTrainer = trainerRepository.save(trainer);
        return trainerMapper.entityToResponseDTO(savedTrainer);
    }

    @Override
    public String deletedById(Integer id) {
        if (!trainerRepository.existsById(id)) {
            throw new TrainerNotFoundException("Trainer not found with id - " + id);
        }

        trainerRepository.deleteById(id);
        return "Deleted trainer with id - " + id;
    }

    @Override
    public TrainerResponseDTO findByFirstame(String trainerName) {
        Trainer trainer = trainerRepository.findByFirstname(trainerName).orElseThrow(() -> new TrainerNotFoundException("Trainer not found with name: " + trainerName));
        return trainerMapper.entityToResponseDTO(trainer);
    }

    @Override
    public List<CourseResponseDTO> seeTrainerCourses(Integer trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new NoSuchElementException("Trainer not found"));

        List<Course> courses = trainer.getCourses();
        return courseMapper.entityToResponseDTO(courses);
    }

    @Override
    public CourseResponseDTO changeTrainerCourse(Integer trainerId, Integer courseId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new NoSuchElementException("Trainer not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

        course.setTrainer(trainer);
        Course savedCourse = courseRepository.save(course);

        return courseMapper.entityToResponseDTO(savedCourse);

    }

    // Trovare trainer che tengono corsi in una certa sala
    @Override
    public List<TrainerResponseDTO> findTrainersByRoomName(String roomName) {
        List<Trainer> trainers = trainerRepository.findTrainersByRoomName(roomName);
        return trainerMapper.entityToResponseDTO(trainers);
    }
}
