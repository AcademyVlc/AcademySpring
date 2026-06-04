package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.CustomerProfileRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerProfileResponseDTO;
import com.example.demo.entity.ristorante.CustomerProfile;
import com.example.demo.mapper.ristorante.CustomerProfileMapper;
import com.example.demo.repository.ristorante.CustomerProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerProfileServiceImplTest {

    @Mock
    private CustomerProfileRepository customerProfileRepository;

    @Mock
    private CustomerProfileMapper customerProfileMapper;

    @InjectMocks
    private CustomerProfileServiceImpl customerProfileService;

    @Test
    void findAll() {
        List<CustomerProfile> customerProfiles = new ArrayList<>();
        customerProfiles.add(new CustomerProfile());

        List<CustomerProfileResponseDTO> customerProfileResponseDTOS = new ArrayList<>();
        CustomerProfileResponseDTO customerProfileResponseDTO = new CustomerProfileResponseDTO();
        customerProfileResponseDTO.setAddress("via giove 15, torino");
        customerProfileResponseDTOS.add(customerProfileResponseDTO);

        when(customerProfileRepository.findAll()).thenReturn(customerProfiles);
        when(customerProfileMapper.entityToResponseDTO(customerProfiles)).thenReturn(customerProfileResponseDTOS);

        List<CustomerProfileResponseDTO> result = customerProfileService.findAll();

        assertEquals("via giove 15, torino", result.get(0).getAddress());
    }

    @Test
    void findById() {
        Integer id = 1;

        CustomerProfile customerProfile = new CustomerProfile();

        CustomerProfileResponseDTO customerProfileResponseDTO = new CustomerProfileResponseDTO();
        customerProfileResponseDTO.setAddress("via giove 15, torino");

        when(customerProfileRepository.findById(id)).thenReturn(Optional.of(customerProfile));
        when(customerProfileMapper.entityToResponseDTO(customerProfile)).thenReturn(customerProfileResponseDTO);

        CustomerProfileResponseDTO result = customerProfileService.findById(id);
        assertEquals("via giove 15, torino", result.getAddress());
    }

    @Test
    void save() {
        CustomerProfileRequestDTO customerProfileRequestDTO = new CustomerProfileRequestDTO();
        customerProfileRequestDTO.setAddress("via giove 15, torino");

        CustomerProfile customerProfile = new CustomerProfile();
        CustomerProfile savedCustomerProfile = new CustomerProfile();

        CustomerProfileResponseDTO customerProfileResponseDTO = new CustomerProfileResponseDTO();
        customerProfileResponseDTO.setAddress("via giove 15, torino");

        when(customerProfileMapper.requestDTOToEntity(customerProfileRequestDTO)).thenReturn(customerProfile);
        when(customerProfileRepository.save(customerProfile)).thenReturn(savedCustomerProfile);
        when(customerProfileMapper.entityToResponseDTO(savedCustomerProfile)).thenReturn(customerProfileResponseDTO);

        CustomerProfileResponseDTO result = customerProfileService.save(customerProfileRequestDTO);
        assertEquals("via giove 15, torino", result.getAddress());
    }

    @Test
    void update() {
        Integer id = 1;
        CustomerProfileRequestDTO customerProfileRequestDTO = new CustomerProfileRequestDTO();
        customerProfileRequestDTO.setAddress("via giove 15, torino");

        CustomerProfile customerProfile = new CustomerProfile();
        CustomerProfile updatedCustomerProfile = new CustomerProfile();

        CustomerProfileResponseDTO customerResponseDTO = new CustomerProfileResponseDTO();
        customerResponseDTO.setAddress("via giove 15, torino");

        when(customerProfileRepository.findById(id)).thenReturn(Optional.of(customerProfile));
        when(customerProfileRepository.save(customerProfile)).thenReturn(updatedCustomerProfile);
        when(customerProfileMapper.entityToResponseDTO(updatedCustomerProfile)).thenReturn(customerResponseDTO);

        CustomerProfileResponseDTO result = customerProfileService.update(id, customerProfileRequestDTO);
        assertEquals("via giove 15, torino", result.getAddress());
    }

    @Test
    void deletedById() {
        doNothing().when(customerProfileRepository).deleteById(anyInt());

        when(customerProfileRepository.existsById(anyInt())).thenReturn(true);
        customerProfileService.deletedById(anyInt());

        when(customerProfileRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> customerProfileService.deletedById(anyInt()));
    }
}