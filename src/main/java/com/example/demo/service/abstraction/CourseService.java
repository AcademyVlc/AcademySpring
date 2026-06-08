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
    // Sposta corso in un’altra sala solo se la sala ha abbastanza capienza
    CourseResponseDTO changeCourseRoomCheckCapacity(Integer courseId, Integer roomId);
    // Elimina corso solo se non ha clienti iscritti
    void deleteCourseOnlyIfZeroSubscribers(Integer courseId);

    // JPQL
    // Restituire tutti i corsi tenuti da un trainer cercando per nome.
    List<CourseResponseDTO> findCoursesByTrainerFirstname(String trainerFirstname);
}
