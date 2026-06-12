package com.example.demo.dto.request;

import com.example.demo.entity.palestra.Room;
import com.example.demo.entity.palestra.Trainer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {

//    private Integer id;

    @NotBlank(message = "name required")
    private String name;

    @NotBlank(message = "level required")
    private String level;

    @NotNull(message = "durationMinutes required")
    private Integer durationMinutes;

    @NotBlank(message = "trainerName required")
    private String trainerName;

    @NotBlank(message = "roomName required")
    private String roomName;
}
