package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.CustomerRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerResponseDTO;
import com.example.demo.services.ristorante.implementation.CustomerServiceImpl;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerServiceImpl customerService;

    @Test
    void findAll() throws Exception {

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName("marco");

        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        customerResponseDTOS.add(customerResponseDTO);

        when(customerService.findAll()).thenReturn(customerResponseDTOS);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("marco"));

        verify(customerService).findAll();
    }

    @Test
    void findById() throws Exception {
        Integer id = 1;

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName("stefano");

        when(customerService.findById(id)).thenReturn(customerResponseDTO);

        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("stefano"));

        when(customerService.findById(id)).thenThrow(new NoSuchElementException("Customer not found"));

        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(status().isNotFound());

        verify(customerService, times(2)).findById(id);
    }

    @Test
    void save() throws Exception {

        String json = """
                {
                  "id": 1,
                  "name": "Marco Rossi",
                  "email": "marco.rossi@email.it"
                }
                """;
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName("stefano");

        when(customerService.save(any(CustomerRequestDTO.class))).thenReturn(customerResponseDTO);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("stefano"));

        verify(customerService).save(any(CustomerRequestDTO.class));

    }

    @Test
    void update() throws Exception {

        Integer id = 1;
        String json = """
                {
                  "id": 1,
                  "name": "Marco Rossi",
                  "email": "marco.rossi@email.it"
                }
                """;

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName("stefano");

        when(customerService.update(eq(id), any(CustomerRequestDTO.class))).thenReturn(customerResponseDTO);

        mockMvc.perform(put("/api/customers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("stefano"));

        verify(customerService).update(eq(id), any(CustomerRequestDTO.class));

    }

    @Test
    void deletedById() throws Exception{
        Integer id = 1;

        mockMvc.perform(delete("/api/customers/{id}", id))
                .andExpect(status().isNoContent());

        verify(customerService).deletedById(id);

    }
}