package com.example.demo.service.implementation;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.CourseRevenueResponseDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.*;
import com.example.demo.mapper.palestra.CourseMapper;
import com.example.demo.mapper.palestra.CustomerMapper;
import com.example.demo.mapper.palestra.TrainerMapper;
import com.example.demo.repository.palestra.CourseRepository;
import com.example.demo.repository.palestra.CustomerRepository;
import com.example.demo.repository.palestra.TrainerRepository;
import com.example.demo.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    // PER IL CUSTOMER
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    // PER IL COURSE
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    // PER IL TRAINER
    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    @Override
    public List<CustomerResponseDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.entityToResponseDTO(customers);
    }

    @Override
    public CustomerResponseDTO findById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not founded"));
        return customerMapper.entityToResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.requestDTOToEntity(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.entityToResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO update(Integer id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not founded"));

        customer.setFirstname(customerRequestDTO.getFirstname());
        customer.setLastname(customerRequestDTO.getLastname());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setBirthdate(customerRequestDTO.getBirthdate());

        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.entityToResponseDTO(savedCustomer);
    }

    @Override
    public String deletedById(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not founded with id - " + id);
        }
        customerRepository.deleteById(id);
        return "Deleted customer with id - " + id;
    }

    // Iscrivere un cliente ad un corso
    @Override
    public CustomerResponseDTO subscribeCustomerToCourse(Integer customerId, Integer courseId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not founded"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not founded"));

        customer.getCourses().add(course);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.entityToResponseDTO(updatedCustomer);
    }

    // Togliere l'iscrizione ad un corso da parte di un cliente
    @Override
    public String unsubscribeCustomerFromCourse(Integer customerId, Integer courseId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

        if (!courseRepository.existsById(courseId)) {
            throw new NoSuchElementException("Customer is not subscribed to this course");
        }

        customer.getCourses().remove(course);

        Customer savedCustomer = customerRepository.save(customer);
        return "Course deleted from customer with id - " + customerId;
    }

    // Vedere tutti i corsi di un cliente
    @Override
    public List<CourseResponseDTO> seeAllCoursesOfCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));

        List<Course> courses = customer.getCourses();

        return courseMapper.entityToResponseDTO(courses);
    }

    // Iscrivi cliente solo se abbonamento è attivo e non è già iscritto a quel corso
    @Override
    public CustomerResponseDTO subscribeOnlyIfActivateSubscriptionAndCourse(Integer customerId, Integer courseId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found")); // Trovo il customer
        Subscription subscription = customer.getSubscription(); // Gli prendo l'iscrizione

        // Evito la NPE di subscription nel caso non esistesse
        if (subscription == null) {
            throw new RuntimeException("Subscription not found");
        }

        // Valuto se è ancora attiva l'iscrizione
        if (subscription.getEndDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Subscription is not active");
        }

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));
        List<Customer> customers = course.getCustomers();

        // Controllo che non sia già iscritto
        if (customers.contains(customer)) {
            throw new RuntimeException("Customer is already subscribed to this course");
        }

        customer.getCourses().add(course);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.entityToResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO subscribeOnlyIfRoomNotFull(Integer customerId, Integer courseId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

        Room room = course.getRoom();
        if (room == null) {
            throw new RuntimeException("Room not exist");
        }

        Integer capacity = room.getCapacity();
        System.out.println(capacity);
        int size = course.getCustomers().size();
        Integer result = capacity - size;

        if (result <= 0) {
            throw new RuntimeException("Room is full!");
        }

        customer.getCourses().add(course);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.entityToResponseDTO(savedCustomer);
    }

    // Trova clienti iscritti a corsi di un certo trainer
    @Override
    public List<CustomerResponseDTO> findCustomerSubscribeAtTrainerCourse(Integer trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new NoSuchElementException("Trainer not found"));

        List<Customer> customers = trainer.getCourses()
                .stream()
                .flatMap(course -> course.getCustomers().stream()) // Flatmap è la stessa cosa di map, solo che serve per schiacciare collezioni dentro collezioni
                .distinct()
                .toList();

        return customerMapper.entityToResponseDTO(customers);
    }

    // Calcola quanto guadagna la palestra da un corso
    @Override
    public CourseRevenueResponseDTO calculateCourseRevenue(Integer courseId) {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found"));

        BigDecimal totalRevenue = course.getCustomers()
                .stream()
                .map(c -> c.getSubscription())
                .filter(subscription -> subscription != null)
                .map(subscription -> subscription.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CourseRevenueResponseDTO.builder()
                .courseName(course.getName())
                .totalCustomer(course.getCustomers().size())
                .totalRevenue(totalRevenue)
                .build();
    }


}
