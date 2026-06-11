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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainerServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainerMapper trainerMapper;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    @Test
    void findAll() {
        List<Trainer> trainerList = new ArrayList<>();

        List<TrainerResponseDTO> trainerResponseDTOS = new ArrayList<>();
        TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO();
        trainerResponseDTO.setFirstname("claudio");
        trainerResponseDTOS.add(trainerResponseDTO);

        when(trainerRepository.findAll()).thenReturn(trainerList);
        when(trainerMapper.entityToResponseDTO(trainerList)).thenReturn(trainerResponseDTOS);

        List<TrainerResponseDTO> result = trainerService.findAll();

        assertEquals("claudio", result.get(0).getFirstname());

        verify(trainerRepository).findAll();
        verify(trainerMapper).entityToResponseDTO(trainerList);
    }

    @Test
    void findById() {
        Integer id = 1;

        Trainer trainer = new Trainer();
        TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO();
        trainerResponseDTO.setId(1);

        when(trainerRepository.findById(id)).thenReturn(Optional.of(trainer));
        when(trainerMapper.entityToResponseDTO(trainer)).thenReturn(trainerResponseDTO);

        TrainerResponseDTO result = trainerService.findById(id);

        assertEquals(1, result.getId());

        verify(trainerRepository).findById(id);
        verify(trainerMapper).entityToResponseDTO(trainer);
    }

    @Test
    void save() {
        TrainerRequestDTO trainerRequestDTO = new TrainerRequestDTO();
        trainerRequestDTO.setFirstname("claudio");
        trainerRequestDTO.setLastname("piras");
        trainerRequestDTO.setSpecialization("bodybuilding");

        Trainer trainer = new Trainer();
        Trainer savedTrainer = new Trainer();
        TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO();
        trainerResponseDTO.setFirstname(trainerRequestDTO.getFirstname());
        trainerResponseDTO.setLastname(trainerRequestDTO.getLastname());
        trainerResponseDTO.setSpecialization(trainerRequestDTO.getSpecialization());

        when(trainerMapper.requestDTOToEntity(trainerRequestDTO)).thenReturn(trainer);
        when(trainerRepository.save(trainer)).thenReturn(savedTrainer);
        when(trainerMapper.entityToResponseDTO(savedTrainer)).thenReturn(trainerResponseDTO);

        TrainerResponseDTO result = trainerService.save(trainerRequestDTO);

        assertEquals("claudio", result.getFirstname());
        assertEquals("piras", result.getLastname());
        assertEquals("bodybuilding", result.getSpecialization());

        verify(trainerMapper).requestDTOToEntity(trainerRequestDTO);
        verify(trainerRepository).save(trainer);
        verify(trainerMapper).entityToResponseDTO(savedTrainer);
    }

    @Test
    void update() {
        Integer id = 1;
        TrainerRequestDTO trainerRequestDTO = new TrainerRequestDTO();
        trainerRequestDTO.setFirstname("claudio");
        trainerRequestDTO.setLastname("piras");
        trainerRequestDTO.setSpecialization("bodybuilding");

        Trainer trainer = new Trainer();
        Trainer savedTrainer = new Trainer();

        TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO();
        trainerResponseDTO.setFirstname(trainerRequestDTO.getFirstname());
        trainerResponseDTO.setLastname(trainerRequestDTO.getLastname());
        trainerResponseDTO.setSpecialization(trainerRequestDTO.getSpecialization());

        when(trainerRepository.findById(id)).thenReturn(Optional.of(trainer));
        when(trainerRepository.save(trainer)).thenReturn(savedTrainer);
        when(trainerMapper.entityToResponseDTO(savedTrainer)).thenReturn(trainerResponseDTO);

        TrainerResponseDTO result = trainerService.update(id, trainerRequestDTO);

        assertEquals("claudio", result.getFirstname());
        assertEquals("piras", result.getLastname());
        assertEquals("bodybuilding", result.getSpecialization());

        verify(trainerRepository).findById(id);
        verify(trainerRepository).save(trainer);
        verify(trainerMapper).entityToResponseDTO(savedTrainer);
    }

    @Test
    void deletedById() {
        Integer id = 1;

        when(trainerRepository.existsById(id)).thenReturn(true);
        doNothing().when(trainerRepository).deleteById(id);

        String result = trainerService.deletedById(id);

        assertEquals("Deleted trainer with id - 1", result);
        verify(trainerRepository).deleteById(id);
    }

    @Test
    void deletedByIdNotFound() {
        Integer id = 1;

        when(trainerRepository.existsById(id)).thenReturn(false);

        assertThrows(TrainerNotFoundException.class, () -> trainerService.deletedById(id));
    }

    @Test
    void seeTrainerCourses() {
        Integer id = 1;
        Trainer trainer = new Trainer();

        List<Course> courses = new ArrayList<>();
        Course course = new Course();
        courses.add(course);
        trainer.setCourses(courses);

        List<CourseResponseDTO> courseResponseDTOS = new ArrayList<>();
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("claudio");
        courseResponseDTOS.add(courseResponseDTO);

        when(trainerRepository.findById(id)).thenReturn(Optional.of(trainer));
        when(courseMapper.entityToResponseDTO(courses)).thenReturn(courseResponseDTOS);

        List<CourseResponseDTO> result = trainerService.seeTrainerCourses(id);

        assertEquals("claudio", result.get(0).getName());

        verify(trainerRepository).findById(id);
        verify(courseMapper).entityToResponseDTO(courses);
    }

    @Test
    void changeTrainerCourse() {
        Integer trainerId = 1;
        Integer courseId = 1;

        Trainer trainer = new Trainer();
        Course savedCourse = new Course();
        Course course = new Course();
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("claudio");

        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(savedCourse);
        when(courseMapper.entityToResponseDTO(savedCourse)).thenReturn(courseResponseDTO);

        CourseResponseDTO result = trainerService.changeTrainerCourse(trainerId, courseId);
        assertEquals("claudio", result.getName());

        verify(trainerRepository).findById(trainerId);
        verify(courseRepository).findById(courseId);
        verify(courseRepository).save(course);
        verify(courseMapper).entityToResponseDTO(savedCourse);
    }

    @Test
    void findTrainersByRoomName() {
        String roomName = "giove";

        List<Trainer> trainers = new ArrayList<>();
        List<TrainerResponseDTO> trainerResponseDTOS = new ArrayList<>();
        TrainerResponseDTO trainerResponseDTO = new TrainerResponseDTO();
        trainerResponseDTO.setFirstname("claudio");
        trainerResponseDTOS.add(trainerResponseDTO);

        when(trainerRepository.findTrainersByRoomName(roomName)).thenReturn(trainers);
        when(trainerMapper.entityToResponseDTO(trainers)).thenReturn(trainerResponseDTOS);

        List<TrainerResponseDTO> result = trainerService.findTrainersByRoomName(roomName);

        assertEquals("claudio", result.get(0).getFirstname());

        verify(trainerRepository).findTrainersByRoomName(roomName);
        verify(trainerMapper).entityToResponseDTO(trainers);
    }
}