package com.example.demo.dto.ristorante.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCountDTO {

    private String category;
    private Long total;
}
//Service:
//public List<CategoryCountDTO> countDishGroupingByCategory() {
//
//    return dishRepository.countDishGroupingByCategory()
//            .stream()
//            .map(row -> new CategoryCountDTO(
//                    (String) row[0],
//                    (Long) row[1]
//            ))
//            .toList();
//}

