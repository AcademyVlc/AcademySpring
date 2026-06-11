package com.example.demo.service.implementation;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.CourseRevenueResponseDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.*;
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

import java.math.BigDecimal;
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
    void deletedByIdNotFound() {
        Integer id = 1;

        when(customerRepository.existsById(id)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> customerService.deletedById(id));
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
        Integer customerid = 1;
        Customer customer = new Customer();

        List<Course> courses = customer.getCourses();
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setName("bodybuilding");
        List<CourseResponseDTO> courseResponseDTOS = new ArrayList<>();
        courseResponseDTOS.add(courseResponseDTO);

        when(customerRepository.findById(customerid)).thenReturn(Optional.of(customer));
        when(courseMapper.entityToResponseDTO(courses)).thenReturn(courseResponseDTOS);

        List<CourseResponseDTO> result = customerService.seeAllCoursesOfCustomer(customerid);

        assertEquals("bodybuilding", result.get(0).getName());

        verify(customerRepository).findById(customerid);
        verify(courseMapper).entityToResponseDTO(courses);
    }

    @Test
    void subscribeOnlyIfActivateSubscriptionAndCourse() {
        Integer customerId = 1;
        Integer courseId = 1;

        Customer customer = new Customer();
        Customer savedCustomer = new Customer();

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("claudio");

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(2027, 6, 11));
        customer.setSubscription(subscription);

        Course course = new Course();
        List<Customer> customers = new ArrayList<>();
//        customers.add(customer); Cosi simulo che il customer è iscritto
        course.setCustomers(customers); // Collego la lista al corso


        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(customerMapper.entityToResponseDTO(savedCustomer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.subscribeOnlyIfActivateSubscriptionAndCourse(customerId, courseId);

        assertEquals("claudio", result.getFirstname());

        verify(customerRepository).findById(customerId);
        verify(courseRepository).findById(courseId);
        verify(customerRepository).save(customer);
        verify(customerMapper).entityToResponseDTO(savedCustomer);
    }

    @Test
    void subscribeOnlyIfRoomNotFull() {
        Integer customerId = 1;
        Integer courseId = 1;

        Customer customer = new Customer();
        Customer savedCustomer = new Customer();
        Course course = new Course();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        Room room = new Room();
        room.setCapacity(100);
        course.setCustomers(customers);
        course.setRoom(room);

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("claudio");


        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(customerMapper.entityToResponseDTO(savedCustomer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.subscribeOnlyIfRoomNotFull(customerId, courseId);
        assertEquals("claudio", result.getFirstname());

        verify(customerRepository).findById(customerId);
        verify(courseRepository).findById(courseId);
        verify(customerRepository).save(customer);
        verify(customerMapper).entityToResponseDTO(savedCustomer);
    }

    @Test
    void findCustomerSubscribeAtTrainerCourse() {
        Integer trainerId = 1;
        Trainer trainer = new Trainer();

        List<Customer> customers = new ArrayList<>();
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("claudio");

        List<CustomerResponseDTO> customersResponse = new ArrayList<>();
        customersResponse.add(customerResponseDTO);
        Customer customer = new Customer();
        customers.add(customer);

        List<Course> courses = new ArrayList<>();
        Course course = new Course();
        courses.add(course);
        course.setCustomers(customers);
        trainer.setCourses(courses);

        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));
        when(customerMapper.entityToResponseDTO(customers)).thenReturn(customersResponse);

        List<CustomerResponseDTO> result = customerService.findCustomerSubscribeAtTrainerCourse(trainerId);

        assertEquals("claudio", result.get(0).getFirstname());

        verify(trainerRepository).findById(trainerId);
        verify(customerMapper).entityToResponseDTO(customers);

    }

    @Test
    void calculateCourseRevenue() {
        Integer courseId = 1;
        Course course = new Course();
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customers.add(customer);
        course.setCustomers(customers);
        course.setName("bodybuilding");


        CourseRevenueResponseDTO courseRevenueResponseDTO = new CourseRevenueResponseDTO();
        courseRevenueResponseDTO.setCourseName("bodybuilding");
        courseRevenueResponseDTO.setTotalCustomer(1);
        courseRevenueResponseDTO.setTotalRevenue(BigDecimal.valueOf(0));

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        CourseRevenueResponseDTO result = customerService.calculateCourseRevenue(courseId);

        assertEquals("bodybuilding", result.getCourseName());
        assertEquals(1, result.getTotalCustomer());
        assertEquals(BigDecimal.valueOf(0), result.getTotalRevenue());

        verify(courseRepository).findById(courseId);
    }

    @Test
    void findCustomersWithActiveSubscription() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setFirstname("claudio");
        customers.add(customer);

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("claudio");

        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        customerResponseDTOS.add(customerResponseDTO);

        when(customerRepository.findCustomersWithActiveSubscription()).thenReturn(customers);
        when(customerMapper.entityToResponseDTO(customers)).thenReturn(customerResponseDTOS);

        List<CustomerResponseDTO> result = customerService.findCustomersWithActiveSubscription();

        assertEquals("claudio", result.get(0).getFirstname());
    }

    @Test
    void findCustomersByCourseName() {
        String courseName = "bodybuilding";
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setFirstname("claudio");
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        customerResponseDTOS.add(customerResponseDTO);
        List<Customer> customers = new ArrayList<>();

        when(customerRepository.findCustomersByCourseName(courseName)).thenReturn(customers);
        when(customerMapper.entityToResponseDTO(customers)).thenReturn(customerResponseDTOS);

        List<CustomerResponseDTO> result = customerService.findCustomersByCourseName(courseName);

        assertEquals("claudio", result.get(0).getFirstname());

        verify(customerRepository).findCustomersByCourseName(courseName);
        verify(customerMapper).entityToResponseDTO(customers);
    }
}