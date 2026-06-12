package com.example.demo.service.abstraction;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.CourseRevenueResponseDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Customer;
import org.apache.coyote.BadRequestException;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> findAll();

    CustomerResponseDTO findById(Integer id);

    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) throws BadRequestException;

    CustomerResponseDTO update(Integer id, CustomerRequestDTO customerRequestDTO);

    String deletedById(Integer id);

    // Iscrivere un cliente ad un corso
    CustomerResponseDTO subscribeCustomerToCourse(Integer customerId, Integer courseId);

    // Togliere l'iscrizione ad un corso da parte di un cliente
    String unsubscribeCustomerFromCourse(Integer customerId, Integer courseId);

    // Vedere tutti i corsi di un cliente
    List<CourseResponseDTO> seeAllCoursesOfCustomer(Integer customerId);

    // Iscrivi cliente solo se abbonamento è attivo e non è già iscritto a quel corso
    CustomerResponseDTO subscribeOnlyIfActivateSubscriptionAndCourse(Integer customerId, Integer courseId);

    // Iscrivi cliente solo se la sala non è piena
    CustomerResponseDTO subscribeOnlyIfRoomNotFull(Integer customerId, Integer courseId);

    // Trova clienti iscritti a corsi di un certo trainer
    List<CustomerResponseDTO> findCustomerSubscribeAtTrainerCourse(Integer trainerId);

    // Calcola quanto guadagna la palestra da un corso
    CourseRevenueResponseDTO calculateCourseRevenue(Integer courseId);

    // Trovare clienti con abbonamento attivo
    List<CustomerResponseDTO> findCustomersWithActiveSubscription();

    // Trovare clienti iscritti a un corso tramite nome corso
    List<CustomerResponseDTO> findCustomersByCourseName(@Param("courseName") String courseName);
}
