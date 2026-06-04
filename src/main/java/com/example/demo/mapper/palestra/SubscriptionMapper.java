package com.example.demo.mapper.palestra;

import com.example.demo.dto.request.SubscriptionRequestDTO;
import com.example.demo.dto.response.SubscriptionResponseDTO;
import com.example.demo.entity.palestra.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponseDTO entityToResponseDTO(Subscription subscription);
    List<SubscriptionResponseDTO> entityToResponseDTO(List<Subscription> subscriptions);

    @Mapping(target = "id", ignore = true)
    Subscription requestDTOToEntity(SubscriptionRequestDTO subscriptionRequestDTO);
}
