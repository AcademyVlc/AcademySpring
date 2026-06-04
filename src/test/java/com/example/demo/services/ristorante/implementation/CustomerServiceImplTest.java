package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.CustomerRequestDTO;
import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerResponseDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.entity.ristorante.Customer;
import com.example.demo.entity.ristorante.Dish;
import com.example.demo.mapper.ristorante.CustomerMapper;
import com.example.demo.repository.ristorante.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void findAll() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());

        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName("mauro");
        customerResponseDTOS.add(customerResponseDTO);

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.entityToResponseDTO(customers)).thenReturn(customerResponseDTOS);

        List<CustomerResponseDTO> result = customerService.findAll();
        assertEquals("mauro", result.get(0).getName());
    }

    @Test
    void findById() {
        Integer id = 1;
        Customer customer = new Customer();

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName("mauro");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapper.entityToResponseDTO(customer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.findById(id);
         assertEquals("mauro", result.getName());
    }

    @Test
    void save() {
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setName("pippo");

        Customer customer = new Customer();
        Customer savedCustomer = new Customer();

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName("pippo");

        when(customerMapper.requestDTOToEntity(customerRequestDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(customerMapper.entityToResponseDTO(savedCustomer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.save(customerRequestDTO);

        assertEquals("pippo", result.getName());
    }

    @Test
    void update() {
        Integer id = 1;
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setName("mauro");

        Customer customer = new Customer();
        Customer updatedCustomer = new Customer();

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName("mauro");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(updatedCustomer);
        when(customerMapper.entityToResponseDTO(updatedCustomer)).thenReturn(customerResponseDTO);

        CustomerResponseDTO result = customerService.update(id, customerRequestDTO);
        assertEquals("mauro", result.getName());

    }

    @Test
    void deletedById() {
        doNothing().when(customerRepository).deleteById(anyInt());

        when(customerRepository.existsById(anyInt())).thenReturn(true);
        customerService.deletedById(anyInt());

        when(customerRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> customerService.deletedById(anyInt()));
    }
}