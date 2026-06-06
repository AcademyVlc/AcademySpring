package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.responsedto.CategoryResponseDTO;
import com.example.demo.services.ristorante.abstraction.CategoryService;
import com.example.demo.services.ristorante.implementation.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryServiceImpl categoryService;

    @Test
    void findAll() throws Exception {

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName("dolce");
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        categoryResponseDTOS.add(categoryResponseDTO);

        when(categoryService.findAll()).thenReturn(categoryResponseDTOS);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("dolce"));

        verify(categoryService).findAll();

    }

    @Test
    void findById() throws Exception{

        Integer id = 1;

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName("dolce");

        when(categoryService.findById(id)).thenReturn(categoryResponseDTO);

        mockMvc.perform(get("/api/categories/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("dolce"));

        when(categoryService.findById(id)).thenThrow(new NoSuchElementException("Category not found"));

        mockMvc.perform(get("/api/categories/{id}", id))
                .andExpect(status().isNotFound());

        verify(categoryService, times(2)).findById(id);
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void deletedById() {
    }
}