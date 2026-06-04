package com.example.demo.mapper.palestra;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.entity.palestra.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "trainerId", source = "trainer.id")
    @Mapping(target = "roomId", source = "room.id")
    CourseResponseDTO entityToResponseDTO(Course course);
    List<CourseResponseDTO> entityToResponseDTO(List<Course> course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trainer.firstname" , source = "trainerName")
    @Mapping(target = "room.name" , source = "roomName")
    Course requestDTOToEntity(CourseRequestDTO courseRequestDTO);
}
