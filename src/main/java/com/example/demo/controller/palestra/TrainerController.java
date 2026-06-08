package com.example.demo.controller.palestra;

import com.example.demo.dto.request.TrainerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.TrainerResponseDTO;
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

    @GetMapping
    public ResponseEntity<List<TrainerResponseDTO>> findAll(){
        List<TrainerResponseDTO> trainers = trainerService.findAll();
        return ResponseEntity.ok(trainers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerResponseDTO> findById(@PathVariable Integer id){
        TrainerResponseDTO trainer = trainerService.findById(id);
        return ResponseEntity.ok(trainer);
    }

    @PostMapping
    public ResponseEntity<TrainerResponseDTO> save(@RequestBody TrainerRequestDTO trainerRequestDTO){
        TrainerResponseDTO savedTrainer = trainerService.save(trainerRequestDTO);
        return ResponseEntity.ok(savedTrainer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerResponseDTO> update(@PathVariable Integer id, @RequestBody TrainerRequestDTO trainerRequestDTO){
        TrainerResponseDTO updatedTrainer = trainerService.update(id, trainerRequestDTO);
        return ResponseEntity.ok(updatedTrainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id){
        trainerService.findById(id);
        return ResponseEntity.noContent().build();
    }
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
