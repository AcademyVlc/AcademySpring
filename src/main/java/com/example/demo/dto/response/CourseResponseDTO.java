package com.example.demo.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO extends CourseResponseWrapperDTO{

    private Integer id;

    private String name;

    private String level;

    private Integer durationMinutes;

    private Integer trainerId;

    private Integer roomId;
}
