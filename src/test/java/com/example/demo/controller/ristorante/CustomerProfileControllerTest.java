package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.CustomerProfileRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerProfileResponseDTO;
import com.example.demo.services.ristorante.implementation.CustomerProfileServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerProfileController.class)
class CustomerProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerProfileServiceImpl customerProfileService;

    @Test
    void findAll() throws Exception{
        CustomerProfileResponseDTO customerProfileResponseDTO = new CustomerProfileResponseDTO();
        customerProfileResponseDTO.setAddress("via giove 15");

        List<CustomerProfileResponseDTO> customerProfileResponseDTOS = new ArrayList<>();
        customerProfileResponseDTOS.add(customerProfileResponseDTO);

        when(customerProfileService.findAll()).thenReturn(customerProfileResponseDTOS);

        mockMvc.perform(get("/api/customers-profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address").value("via giove 15"));

        verify(customerProfileService).findAll();
    }

    @Test
    void findById() throws Exception{
        Integer id = 1;
        CustomerProfileResponseDTO customerProfileResponseDTO = new CustomerProfileResponseDTO();
        customerProfileResponseDTO.setAddress("via giove 15");

        when(customerProfileService.findById(id)).thenReturn(customerProfileResponseDTO);

        mockMvc.perform(get("/api/customers-profile/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("via giove 15"));

        when(customerProfileService.findById(id)).thenThrow(new NoSuchElementException("CustomerProfile not found"));
        mockMvc.perform(get("/api/customers-profile/{id}", id))
                .andExpect(status().isNotFound());

        verify(customerProfileService, times(2)).findById(id);
    }

    @Test
    void save() throws Exception{

        String json = """
                {
                  "id": 1,
                  "phone": "3331234567",
                  "address": "Via Roma 12, Torino",
                  "customerId": 1
                }
                """;

        CustomerProfileResponseDTO customerProfileResponseDTO = new CustomerProfileResponseDTO();
        customerProfileResponseDTO.setAddress("via giove 15");

        when(customerProfileService.save(any(CustomerProfileRequestDTO.class))).thenReturn(customerProfileResponseDTO);

        mockMvc.perform(post("/api/customers-profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("via giove 15"));

        verify(customerProfileService).save(any(CustomerProfileRequestDTO.class));
    }

    @Test
    void update() throws Exception{
        Integer id = 1;

        String json = """
                {
                  "id": 1,
                  "phone": "3331234567",
                  "address": "Via Roma 12, Torino",
                  "customerId": 1
                }
                """;

        CustomerProfileResponseDTO customerProfileResponseDTO = new CustomerProfileResponseDTO();
        customerProfileResponseDTO.setAddress("via giove 15");

        when(customerProfileService.update(eq(id), any(CustomerProfileRequestDTO.class))).thenReturn(customerProfileResponseDTO);

        mockMvc.perform(put("/api/customers-profile/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("via giove 15"));
    }

    @Test
    void deletedById() throws Exception{
        Integer id = 1;

        mockMvc.perform(delete("/api/customers-profile/{id}", id))
                .andExpect(status().isNoContent());

        verify(customerProfileService).deletedById(id);
    }
}