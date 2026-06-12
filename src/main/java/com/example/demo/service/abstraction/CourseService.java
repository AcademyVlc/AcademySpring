package com.example.demo.service.abstraction;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseCustomerCountDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.entity.palestra.Course;
import com.example.demo.service.abstraction.generic_service.GenericService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseService extends GenericService<CourseResponseDTO, CourseRequestDTO, Integer> {
//    List<CourseResponseDTO> findAll();
//
//    CourseResponseDTO findById(Integer id);
//
//    CourseResponseDTO save(CourseRequestDTO courseRequestDTO);
//
//    CourseResponseDTO update(Integer id, CourseRequestDTO courseRequestDTO);
//
//    String deletedById(Integer id);

    // Sposta corso in un’altra sala solo se la sala ha abbastanza capienza
    CourseResponseDTO changeCourseRoomCheckCapacity(Integer courseId, Integer roomId);

    // Elimina corso solo se non ha clienti iscritti
    void deleteCourseOnlyIfZeroSubscribers(Integer courseId);

    // JPQL
    // Restituire tutti i corsi tenuti da un trainer cercando per nome.
    List<CourseResponseDTO> findCoursesByTrainerFirstname(String trainerFirstname);

    // Trovare corsi con durata maggiore di X minuti
    List<CourseResponseDTO> findCoursesLongerThan(Integer minutes);

    // Contare quanti clienti sono iscritti a ogni corso
    List<CourseCustomerCountDTO> countCustomersByCourse();

    // Trovare corsi con almeno X iscritti
    List<CourseResponseDTO> findCoursesWithAtLeastCustomers(@Param("min") Long min);

}
