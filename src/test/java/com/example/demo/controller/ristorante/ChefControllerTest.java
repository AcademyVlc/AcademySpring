package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.ChefRequestDTO;
import com.example.demo.dto.ristorante.responsedto.ChefResponseDTO;
import com.example.demo.services.ristorante.implementation.ChefServiceImpl;
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

@WebMvcTest(ChefController.class)
class ChefControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ChefServiceImpl chefService;

    @Test
    void findAll() throws Exception {
        ChefResponseDTO chefResponseDTO = new ChefResponseDTO();
        chefResponseDTO.setName("bruno");

        List<ChefResponseDTO> chefResponseDTOS = new ArrayList<>();
        chefResponseDTOS.add(chefResponseDTO);

        when(chefService.findAll()).thenReturn(chefResponseDTOS);

        mockMvc.perform(get("/api/chefs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("bruno"));

        verify(chefService).findAll();
    }

    @Test
    void findById() throws Exception {
        Integer id = 1;
        ChefResponseDTO chefResponseDTO = new ChefResponseDTO();
        chefResponseDTO.setName("bruno");

        when(chefService.findById(id)).thenReturn(chefResponseDTO);

        mockMvc.perform(get("/api/chefs/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("bruno"));

        when(chefService.findById(id)).thenThrow(new NoSuchElementException("Chef not found"));
        mockMvc.perform(get("/api/chefs/{id}", id))
                .andExpect(status().isNotFound());

        verify(chefService, times(2)).findById(id);
    }

    @Test
    void save() throws Exception {
        String json = """
                  {
                  "name": "Stefano Piras",
                  "specialization": "Gourmet"
                  }
                """;

        ChefResponseDTO chefResponseDTO = new ChefResponseDTO();
        chefResponseDTO.setName("Stefano");

        when(chefService.save(any(ChefRequestDTO.class))).thenReturn(chefResponseDTO);

        mockMvc.perform(
                        post("/api/chefs")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());

        verify(chefService).save(any(ChefRequestDTO.class));
    }

    @Test
    void update() throws Exception {
        Integer id = 1;
        String json = """
                  {
                      "name": "Stefano Piras",
                      "specialization": "Gourmet"
                      }
                """;

        ChefResponseDTO chefResponseDTO = new ChefResponseDTO();
        chefResponseDTO.setName("stefano");

        when(chefService.update(eq(id), any(ChefRequestDTO.class))).thenReturn(chefResponseDTO);

        mockMvc.perform(put("/api/chefs/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("stefano"));

        verify(chefService).update(eq(id), any(ChefRequestDTO.class));
    }

    @Test
    void deletedById() throws Exception{

        Integer id = 1;

        mockMvc.perform(delete("/api/chefs/{id}", id))
                .andExpect(status().isNoContent());

        verify(chefService).deletedById(id);
    }
}