package com.example.demo.service.implementation;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Course;
import com.example.demo.entity.palestra.Customer;
import com.example.demo.mapper.palestra.CourseMapper;
import com.example.demo.mapper.palestra.CustomerMapper;
import com.example.demo.repository.palestra.CourseRepository;
import com.example.demo.repository.palestra.CustomerRepository;
import com.example.demo.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        if (!customerRepository.existsById(id)){
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



}
