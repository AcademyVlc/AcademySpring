package com.example.demo.controller.palestra;

import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.service.implementation.TrainerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerServiceImpl trainerService;

    // Trova i corsi che fa un trainer
    @GetMapping("/{trainerId}/courses")
    public ResponseEntity<List<CourseResponseDTO>> seeTrainerCourses(@PathVariable Integer trainerId){
        List<CourseResponseDTO> courseResponseDTOS = trainerService.seeTrainerCourses(trainerId);
        return ResponseEntity.ok(courseResponseDTOS);
    }

    // Cambia trainer a un corso
    @PatchMapping("/{trainerId}/courses/{courseId}")
    public ResponseEntity<CourseResponseDTO> changeTrainerCourse(@PathVariable Integer trainerId, @PathVariable Integer courseId){
        CourseResponseDTO courseResponseDTO = trainerService.changeTrainerCourse(trainerId, courseId);
        return ResponseEntity.ok(courseResponseDTO);
    }
}
