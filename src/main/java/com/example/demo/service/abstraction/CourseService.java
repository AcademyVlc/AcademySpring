package com.example.demo.service.abstraction;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    List<CourseResponseDTO> findAll();
    CourseResponseDTO findById(Integer id);
    CourseResponseDTO save(CourseRequestDTO courseRequestDTO);
    CourseResponseDTO update(Integer id, CourseRequestDTO courseRequestDTO);
    String deletedById(Integer id);
}
