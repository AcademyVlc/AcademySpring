package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.CustomerRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerResponseDTO;
import com.example.demo.entity.ristorante.Customer;
import com.example.demo.mapper.ristorante.CustomerMapper;
import com.example.demo.repository.ristorante.CustomerRepository;
import com.example.demo.services.ristorante.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public List<CustomerResponseDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.entityToResponseDTO(customers);
    }

    @Override
    public CustomerResponseDTO findById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not founded customer with id - " + id));
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

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        // altri campi...

        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.entityToResponseDTO(updatedCustomer);
    }

    @Override
    public String deletedById(Integer id) {

        if (!customerRepository.existsById(id)){
        throw new RuntimeException("Customer not founded");
        }

       customerRepository.deleteById(id);
       return "Deleted customer with id - " + id;
    }
}
