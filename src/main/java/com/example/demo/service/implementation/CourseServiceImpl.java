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
        return null;
    }

    @Override
    public CourseResponseDTO save(CourseRequestDTO courseRequestDTO) {
        return null;
    }

    @Override
    public CourseRequestDTO update(Integer id, CourseRequestDTO courseRequestDTO) {
        return null;
    }

    @Override
    public String deletedById(Integer id) {
        return "";
    }
}
