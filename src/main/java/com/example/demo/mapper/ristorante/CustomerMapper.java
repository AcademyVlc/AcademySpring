package com.example.demo.mapper.ristorante;

import com.example.demo.dto.ristorante.requestdto.CustomerRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerResponseDTO;
import com.example.demo.entity.ristorante.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO entityToResponseDTO(Customer customer);
    List<CustomerResponseDTO> entityToResponseDTO(List<Customer> customers);

    Customer requestDTOToEntity(CustomerRequestDTO customerRequestDTO);
}
