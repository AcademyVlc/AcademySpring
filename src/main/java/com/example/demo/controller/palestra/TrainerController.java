package com.example.demo.controller.palestra;

import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.service.implementation.TrainerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerServiceImpl trainerService;

    @GetMapping("/{trainerId}/courses")
    public ResponseEntity<List<CourseResponseDTO>> seeTrainerCourses(@PathVariable Integer trainerId){
        List<CourseResponseDTO> courseResponseDTOS = trainerService.seeTrainerCourses(trainerId);
        return ResponseEntity.ok(courseResponseDTOS);
    }
}
