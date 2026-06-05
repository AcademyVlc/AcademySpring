package com.example.demo.service.implementation;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.entity.palestra.Course;
import com.example.demo.mapper.palestra.CourseMapper;
import com.example.demo.repository.palestra.CourseRepository;
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
        if (courseRepository.existsById(id)){
            throw new RuntimeException("Course not found with id -" + id);
        }
        courseRepository.deleteById(id);
        return "Deleted course with id - " + id;
    }
}
