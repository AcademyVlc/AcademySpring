package com.example.demo.service.implementation;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.entity.palestra.Course;
import com.example.demo.entity.palestra.Room;
import com.example.demo.mapper.palestra.CourseMapper;
import com.example.demo.mapper.palestra.RoomMapper;
import com.example.demo.repository.palestra.CourseRepository;
import com.example.demo.repository.palestra.RoomRepository;
import com.example.demo.service.abstraction.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public List<CourseResponseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.entityToResponseDTO(courses);
    }

    @Override
    public CourseResponseDTO findById(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Course not founded"));
        return courseMapper.entityToResponseDTO(course);
    }

    @Override
    public CourseResponseDTO save(CourseRequestDTO courseRequestDTO) {
        Course course = courseMapper.requestDTOToEntity(courseRequestDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.entityToResponseDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO update(Integer id, CourseRequestDTO courseRequestDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Course not found"));

        course.setName(courseRequestDTO.getName());
        course.setLevel(courseRequestDTO.getLevel());
        course.setDurationMinutes(courseRequestDTO.getDurationMinutes());

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.entityToResponseDTO(updatedCourse);
    }

    @Override
    public String deletedById(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id -" + id);
        }
        courseRepository.deleteById(id);
        return "Deleted course with id - " + id;
    }

    // Sposta corso in un’altra sala solo se la sala ha abbastanza capienza
    @Override
    public CourseResponseDTO changeCourseRoomCheckCapacity(Integer courseId, Integer roomId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course Not found"));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException("Room not found"));

        Integer subscribers = course.getCustomers().size();
        Integer roomCapacity = room.getCapacity();

        Integer result = roomCapacity - subscribers;

        if (result <= 0) {
            throw new RuntimeException("Room capacity not enough to host subscribers");
        }

        course.setRoom(room);

        Course savedCourse = courseRepository.save(course);
        return courseMapper.entityToResponseDTO(savedCourse);
    }

    // Elimina corso solo se non ha clienti iscritti
    @Override
    public void deleteCourseOnlyIfZeroSubscribers(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

        boolean empty = course.getCustomers().isEmpty();

        if (!empty) {
            throw new RuntimeException("You cant't delete the course because it has subscribers");
        }

        courseRepository.deleteById(courseId);
    }


}
