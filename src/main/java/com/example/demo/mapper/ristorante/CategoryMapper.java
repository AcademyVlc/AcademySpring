package com.example.demo.mapper.ristorante;

import com.example.demo.dto.ristorante.CategoryRequestDTO;
import com.example.demo.dto.ristorante.CategoryResponseDTO;
import com.example.demo.entity.ristorante.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    CategoryResponseDTO entityToResponseDTO(Category category);
    List<CategoryResponseDTO> entityToResponseDTO(List<Category> categories);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dishes", ignore = true)
    Category requestDTOToEntity(CategoryRequestDTO categoryRequestDTO);
    List<Category> requestDTOToEntity(List<CategoryRequestDTO> categoryRequestDTO);
}
