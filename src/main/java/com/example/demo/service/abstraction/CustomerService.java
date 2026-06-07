package com.example.demo.service.abstraction;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> findAll();

    CustomerResponseDTO findById(Integer id);

    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO update(Integer id, CustomerRequestDTO customerRequestDTO);

    String deletedById(Integer id);

    // Iscrivere un cliente ad un corso
    CustomerResponseDTO subscribeCustomerToCourse(Integer customerId, Integer courseId);

    // Togliere l'iscrizione ad un corso da parte di un cliente
    String unsubscribeCustomerFromCourse(Integer customerId, Integer courseId);

    // Vedere tutti i corsi di un cliente
    List<CourseResponseDTO> seeAllCoursesOfCustomer(Integer customerId);
}
