package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.CategoryRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CategoryResponseDTO;
import com.example.demo.entity.ristorante.Category;
import com.example.demo.mapper.ristorante.CategoryMapper;
import com.example.demo.repository.ristorante.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void findAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        List<CategoryResponseDTO> categoriesDTO = new ArrayList<>();
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
        categoryDTO.setName("pasta");
        categoriesDTO.add(categoryDTO);

        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryMapper.entityToResponseDTO(categories)).thenReturn(categoriesDTO);

        List<CategoryResponseDTO> result = categoryService.findAll();
        assertEquals(1, result.size());
        assertEquals("pasta", result.get(0).getName());
    }

    @Test
    void findById() {
        Integer id = 1;
        Category category = new Category();

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName("pasta");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryMapper.entityToResponseDTO(category)).thenReturn(categoryResponseDTO);

        CategoryResponseDTO result = categoryService.findById(id);
        assertEquals("pasta", result.getName());
    }

    @Test
    void save() {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setName("legumi");

        Category category = new Category();
        Category savedCategory = new Category();

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName("legumi");

        when(categoryMapper.requestDTOToEntity(categoryRequestDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(savedCategory);
        when(categoryMapper.entityToResponseDTO(savedCategory)).thenReturn(categoryResponseDTO);

        CategoryResponseDTO result = categoryService.save(categoryRequestDTO);
        assertEquals("legumi", result.getName());
    }

    @Test
    void update() {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setName("legumi");

        Category category = new Category();
        Category updatedCategory = new Category();

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName("legumi");

        when(categoryMapper.requestDTOToEntity(categoryRequestDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(updatedCategory);
        when(categoryMapper.entityToResponseDTO(updatedCategory)).thenReturn(categoryResponseDTO);

        CategoryResponseDTO result = categoryService.save(categoryRequestDTO);
        assertEquals("legumi", result.getName());
    }

    @Test
    void deletedById() {
        doNothing().when(categoryRepository).deleteById(anyInt());

        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        categoryService.deletedById(18);

        when(categoryRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> categoryService.deletedById(anyInt()));
    }
}