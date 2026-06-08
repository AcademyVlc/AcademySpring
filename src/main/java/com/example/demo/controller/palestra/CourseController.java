package com.example.demo.controller.palestra;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.service.abstraction.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> findAll(){
        List<CourseResponseDTO> courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable Integer id){
        CourseResponseDTO customer = courseService.findById(id);
        customer.setSuccess(true);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> save(@RequestBody CourseRequestDTO courseRequestDTO){
        CourseResponseDTO savedCourse = courseService.save(courseRequestDTO);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> update(@PathVariable Integer id, @RequestBody CourseRequestDTO courseRequestDTO){
        CourseResponseDTO updatedCourse = courseService.update(id, courseRequestDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable Integer id){
        courseService.deletedById(id);
        return ResponseEntity.noContent().build();
    }

    // Sposta corso in un’altra sala solo se la sala ha abbastanza capienza
    @PatchMapping("/{courseId}/room/{roomId}")
    public ResponseEntity<CourseResponseDTO> changeCourseRoomCheckCapacity(@PathVariable Integer courseId, @PathVariable Integer roomId){
        CourseResponseDTO courseResponseDTO = courseService.changeCourseRoomCheckCapacity(courseId, roomId);
        return ResponseEntity.ok(courseResponseDTO);
    }

    // Elimina corso solo se non ha clienti iscritti
    @DeleteMapping("{courseId}")
    public ResponseEntity<Void> deleteCourseOnlyIfZeroSubscribers(@PathVariable Integer courseId){
        courseService.deleteCourseOnlyIfZeroSubscribers(courseId);
        return  ResponseEntity.noContent().build();
    }

    // JPQL
    // Restituire tutti i corsi tenuti da un trainer cercando per nome
    @GetMapping("find-courses-by-trainer-firstname")
    public ResponseEntity<List<CourseResponseDTO>> findCoursesByTrainerFirstname(@PathVariable String trainerFirstname){
        List<CourseResponseDTO> courses = courseService.findCoursesByTrainerFirstname(trainerFirstname);
        return ResponseEntity.ok(courses);
    }
}
