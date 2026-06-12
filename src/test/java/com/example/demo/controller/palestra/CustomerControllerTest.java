package com.example.demo.controller.palestra;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.service.abstraction.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Test
    void findAll() throws Exception{
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setFirstname("Mario");

        List<CustomerResponseDTO> customers = new ArrayList<>();
        customers.add(dto);

        when(customerService.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("Mario"));

        verify(customerService).findAll();
    }

    @Test
    void findById() throws Exception{
        Integer id = 1;

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("mario");

        when(customerService.findById(id)).thenReturn(customerResponseDTO);

        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("mario"));

        verify(customerService).findById(id);
    }

    @Test
    void save() throws Exception{
        String json = """
                {
                  "firstname": "Mario",
                  "lastname": "Rossi",
                  "email": "mario.rossi@gmail.com",
                  "birthdate": "2000-01-01"
                }
                """;

        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setFirstname("Mario");

        when(customerService.save(any(CustomerRequestDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Mario"));

        verify(customerService).save(any(CustomerRequestDTO.class));
    }

    @Test
    void update() {
//        String json = """
//                {
//                    "firstname": "Mario",
//                    "lastname": "Rossi",
//                    "email": "mario.rossi@email.it"
//                }
//                """;
//
//        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
//        customerResponseDTO
    }

    @Test
    void deletedByID() {
    }

    @Test
    void subscribeCustomerToCourse() {
    }

    @Test
    void unsubscribeCustomerFromCourse() {
    }

    @Test
    void seeAllCoursesOfCustomer() {
    }

    @Test
    void subscribeOnlyIfActivateSubscriptionAndCourse() {
    }

    @Test
    void subscribeOnlyIfRoomNotFull() {
    }

    @Test
    void findCustomerSubscribeAtTrainerCourse() {
    }

    @Test
    void calculateCourseRevenue() {
    }

    @Test
    void findCustomersWithActiveSubscription() {
    }

    @Test
    void findCustomersByCourseName() {
    }
}