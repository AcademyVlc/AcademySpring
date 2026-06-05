package com.example.demo.mapper.palestra;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.entity.palestra.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerResponseDTO entityToResponseDTO(Customer customer);
    List<CustomerResponseDTO> entityToResponseDTO(List<Customer> customers);

    @Mapping(target = "id", ignore = true )
    Customer requestDTOToEntity(CustomerRequestDTO customerRequestDTO);
}
