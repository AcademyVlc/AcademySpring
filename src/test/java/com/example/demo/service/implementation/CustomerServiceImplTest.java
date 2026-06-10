package com.example.demo.service.implementation;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Course;
import com.example.demo.entity.palestra.Customer;
import com.example.demo.exception_handling.palestra.exceptions.CustomerNotFoundException;
import com.example.demo.mapper.palestra.CourseMapper;
import com.example.demo.mapper.palestra.CustomerMapper;
import com.example.demo.mapper.palestra.TrainerMapper;
import com.example.demo.repository.palestra.CourseRepository;
import com.example.demo.repository.palestra.CustomerRepository;
import com.example.demo.repository.palestra.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainerMapper trainerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void findAll() {
        List<Customer> customers = new ArrayList<>();

        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("claudio");
        customerResponseDTOS.add(customerResponseDTO);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.entityToResponseDTO(customers)).thenReturn(customerResponseDTOS);

        List<CustomerResponseDTO> result = customerService.findAll();

        assertEquals("claudio", result.get(0).getFirstname());

        verify(customerRepository).findAll();
        verify(customerMapper).entityToResponseDTO(customers);
    }

    @Test
    void findById() {
        Integer id = 1;

        Customer customer = new Customer();

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("claudio");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapper.entityToResponseDTO(customer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.findById(id);

        assertEquals("claudio", result.getFirstname());

        verify(customerRepository).findById(id);
        verify(customerMapper).entityToResponseDTO(customer);
    }

    @Test
    void findByIdNotFound() {
        Integer id = 1;

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(id));
    }

    @Test
    void save() {

        Customer customer = new Customer();
        Customer savedCustomer = new Customer();
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("claudio");

        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setFirstname("claudio");

        when(customerMapper.requestDTOToEntity(customerRequestDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(customerMapper.entityToResponseDTO(savedCustomer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.save(customerRequestDTO);

        assertEquals("claudio", result.getFirstname());

        verify(customerMapper).requestDTOToEntity(customerRequestDTO);
        verify(customerRepository).save(customer);
        verify(customerMapper).entityToResponseDTO(savedCustomer);
    }

    @Test
    void update() {
        Integer id = 1;

        Customer customer = new Customer();
        Customer savedCustomer = new Customer();

        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setFirstname("mario");
        customerRequestDTO.setLastname("bisio");
        customerRequestDTO.setEmail("@gmail");
        customerRequestDTO.setBirthdate(LocalDate.of(2026, 6, 10));

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("mario");
        customerResponseDTO.setLastname("bisio");
        customerResponseDTO.setEmail("@gmail");
        customerResponseDTO.setBirthdate(LocalDate.of(2026, 6, 10));

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(customerMapper.entityToResponseDTO(savedCustomer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.update(id, customerRequestDTO);

        assertEquals("mario", result.getFirstname());
        assertEquals("bisio", result.getLastname());
        assertEquals("@gmail", result.getEmail());
        assertEquals(LocalDate.of(2026, 6, 10), result.getBirthdate());

        verify(customerRepository).findById(id);
        verify(customerRepository).save(customer);
        verify(customerMapper).entityToResponseDTO(savedCustomer);

    }

    @Test
    void deletedById() {
        Integer id = 1;

        when(customerRepository.existsById(id)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(id);

        String result = customerService.deletedById(id);
        assertEquals("Deleted customer with id - 1", result);
        verify(customerRepository).deleteById(id);
    }

    @Test
    void subscribeCustomerToCourse() {
        Integer customerId = 1;
        Integer courseId = 1;

        Customer customer = new Customer();
        Customer updatedCustomer = new Customer();
        Course course = new Course();

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        List<Course> courses = new ArrayList<>();
        customer.setCourses(courses);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(customerRepository.save(customer)).thenReturn(updatedCustomer);
        when(customerMapper.entityToResponseDTO(updatedCustomer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.subscribeCustomerToCourse(customerId, courseId);

        assertEquals(customerResponseDTO, result);
        assertTrue(customer.getCourses().contains(course));

        verify(customerRepository).findById(customerId);
        verify(courseRepository).findById(courseId);
        verify(customerRepository).save(customer);
        verify(customerMapper).entityToResponseDTO(updatedCustomer);
    }

    @Test
    void unsubscribeCustomerFromCourse() {
        Integer customerId = 1;
        Integer courseId = 1;

        Customer customer = new Customer();
        Course course = new Course();

        List<Course> courses = new ArrayList<>();
        courses.add(course);
        customer.setCourses(courses);

        when(customerRepository.findById(customerId))
                .thenReturn(Optional.of(customer));

        when(courseRepository.findById(courseId))
                .thenReturn(Optional.of(course));

        when(courseRepository.existsById(courseId))
                .thenReturn(true);

        when(customerRepository.save(customer))
                .thenReturn(customer);

        String result = customerService.unsubscribeCustomerFromCourse(customerId, courseId);

        assertEquals("Course deleted from customer with id - " + customerId, result);
        assertFalse(customer.getCourses().contains(course));

        verify(customerRepository).findById(customerId);
        verify(courseRepository).findById(courseId);
        verify(courseRepository).existsById(courseId);
        verify(customerRepository).save(customer);
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