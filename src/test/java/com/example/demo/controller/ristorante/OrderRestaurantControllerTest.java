package com.example.demo.controller.ristorante;

import com.example.demo.dto.ristorante.requestdto.OrderRestaurantRequestDTO;
import com.example.demo.dto.ristorante.responsedto.OrderRestaurantResponseDTO;
import com.example.demo.services.ristorante.abstraction.OrderRestaurantService;
import com.example.demo.services.ristorante.implementation.OrderRestaurantServiceImpl;
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

@WebMvcTest(OrderRestaurantController.class)
class OrderRestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderRestaurantServiceImpl orderRestaurantService;

    @Test
    void findAll() throws Exception {
        OrderRestaurantResponseDTO orderRestaurantResponseDTO = new OrderRestaurantResponseDTO();
        orderRestaurantResponseDTO.setId(1);

        List<OrderRestaurantResponseDTO> orderRestaurantResponseDTOS = new ArrayList<>();
        orderRestaurantResponseDTOS.add(orderRestaurantResponseDTO);

        when(orderRestaurantService.findAll()).thenReturn(orderRestaurantResponseDTOS);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));

        verify(orderRestaurantService).findAll();
    }

    @Test
    void findById() throws Exception {
        Integer id = 1;
        OrderRestaurantResponseDTO orderRestaurantResponseDTO = new OrderRestaurantResponseDTO();
        orderRestaurantResponseDTO.setId(1);

        when(orderRestaurantService.findById(id)).thenReturn(orderRestaurantResponseDTO);

        mockMvc.perform(get("/api/orders/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        when(orderRestaurantService.findById(id)).thenThrow(new NoSuchElementException("Order not found"));

        mockMvc.perform(get("/api/orders/{id}", id))
                .andExpect(status().isNotFound());

        verify(orderRestaurantService, times(2)).findById(id);
    }

    @Test
    void save() throws Exception {

        String json = """
                {
                  "id": 0,
                  "qtyProducts": 0,
                  "totalPrice": 0,
                  "customerId": 0
                }
                """;
        OrderRestaurantResponseDTO orderRestaurantResponseDTO = new OrderRestaurantResponseDTO();
        orderRestaurantResponseDTO.setId(1);

        when(orderRestaurantService.save(any(OrderRestaurantRequestDTO.class)))
                .thenReturn(orderRestaurantResponseDTO);

        mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());

        verify(orderRestaurantService).save(any(OrderRestaurantRequestDTO.class));

    }

    @Test
    void update() throws Exception{

        Integer id = 1;

        String json = """
                {
                  "id": 0,
                  "qtyProducts": 0,
                  "totalPrice": 0,
                  "customerId": 0
                }
                """;

        OrderRestaurantResponseDTO orderRestaurantResponseDTO = new OrderRestaurantResponseDTO();
        orderRestaurantResponseDTO.setCustomerId(1);

        when(orderRestaurantService.update(eq(id), any(OrderRestaurantRequestDTO.class))).thenReturn(orderRestaurantResponseDTO);

        mockMvc.perform(
                put("/api/orders/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1));

        verify(orderRestaurantService).update(eq(id), any(OrderRestaurantRequestDTO.class));
    }

    @Test
    void deleteById() throws Exception{

        Integer id = 1;

        mockMvc.perform(delete("/api/orders/{id}", id))
                .andExpect(status().isNoContent());

        verify(orderRestaurantService).deletedById(id);
    }
}