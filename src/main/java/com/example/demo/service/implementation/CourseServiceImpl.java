package com.example.demo.service.implementation;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.service.abstraction.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Override
    public List<CourseResponseDTO> findAll() {
        return List.of();
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
