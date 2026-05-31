package com.example.demo.dto.ristorante.responsedto;

import com.example.demo.entity.ristorante.Category;
import com.example.demo.entity.ristorante.Chef;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishResponseDTO {

    private Integer id;

    private String name;

    private Double price;

    private boolean available;

    private String categoryName;

    private String chefName;

//    @ManyToOne
//    @JoinColumn (name = "category_id") // @JoinColumn deve indicare: la colonna FK presente nella tabella corrente
//    private Category category;

//    @ManyToOne
//    @JoinColumn(name = "chef_id")
//    private Chef chef;
}
