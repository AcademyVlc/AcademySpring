package com.example.demo.dto.request;

import com.example.demo.entity.palestra.Room;
import com.example.demo.entity.palestra.Trainer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {

//    private Integer id;

    private String name;

    private String level;

    private Integer durationMinutes;

    private String trainerName;

    private String roomName;
}
