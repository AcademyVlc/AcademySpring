package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.CustomerProfileRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerProfileResponseDTO;
import com.example.demo.entity.ristorante.CustomerProfile;
import com.example.demo.mapper.ristorante.CustomerProfileMapper;
import com.example.demo.repository.ristorante.CustomerProfileRepository;
import com.example.demo.services.exceptions.ChefNotFoundedException;
import com.example.demo.services.ristorante.abstraction.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerProfileMapper customerProfileMapper;


    @Override
    public List<CustomerProfileResponseDTO> findAll() {
        List<CustomerProfile> customerProfiles = customerProfileRepository.findAll();
        return customerProfileMapper.entityToResponseDTO(customerProfiles);
    }

    @Override
    public CustomerProfileResponseDTO findById(Integer id) {
        CustomerProfile customerProfile = customerProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer profile not founded"));
        return customerProfileMapper.entityToResponseDTO(customerProfile);
    }

    @Override
    public CustomerProfileResponseDTO save(CustomerProfileRequestDTO customerProfileRequestDTO) {
        CustomerProfile customerProfile = customerProfileMapper.requestDTOToEntity(customerProfileRequestDTO);

        CustomerProfile savedCustomerProfile = customerProfileRepository.save(customerProfile);

        return customerProfileMapper.entityToResponseDTO(savedCustomerProfile);
    }

    @Override
    public CustomerProfileResponseDTO update(Integer id, CustomerProfileRequestDTO customerProfileRequestDTO) {
        CustomerProfile customerProfile = customerProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer profile not founded"));

//        customerProfile.setCustomer(customerProfileRequestDTO.getCustomer());
        customerProfile.setPhone(customerProfileRequestDTO.getPhone());
        customerProfile.setAddress(customerProfileRequestDTO.getAddress());

        CustomerProfile updatedCustomerProfile = customerProfileRepository.save(customerProfile);
        return customerProfileMapper.entityToResponseDTO(updatedCustomerProfile);
    }

    @Override
    public String deletedById(Integer id) {

        if (!customerProfileRepository.existsById(id)){
            throw new ChefNotFoundedException("Customer's profile not founded with id - " + id);
        }
        customerProfileRepository.deleteById(id);
        return "Deleted customer's profile with id - " + id;
    }
}
