package com.example.demo.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDTO extends ErrorResponseDTO{

    private Integer id;

    private String name;

    private Integer capacity;
}
