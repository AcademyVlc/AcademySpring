package com.example.demo.mapper.ristorante;

import com.example.demo.dto.ristorante.requestdto.CustomerProfileRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CustomerProfileResponseDTO;
import com.example.demo.entity.ristorante.CustomerProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerProfileMapper {

    @Mapping(target = "customerId", source = "customer.id" )
    CustomerProfileResponseDTO entityToResponseDTO (CustomerProfile customerProfile);
    List<CustomerProfileResponseDTO> entityToResponseDTO (List<CustomerProfile> customerProfile);


    CustomerProfile requestDTOToEntity(CustomerProfileRequestDTO customerProfileRequestDTO);
}
