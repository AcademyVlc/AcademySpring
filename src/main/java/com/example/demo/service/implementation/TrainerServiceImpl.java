package com.example.demo.service.implementation;

import com.example.demo.dto.request.TrainerRequestDTO;
import com.example.demo.dto.response.TrainerResponseDTO;
import com.example.demo.entity.palestra.Trainer;
import com.example.demo.service.abstraction.TrainerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Override
    public List<TrainerResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public TrainerResponseDTO findById() {
        return null;
    }

    @Override
    public TrainerResponseDTO save(Trainer trainer) {
        return null;
    }

    @Override
    public TrainerResponseDTO update(Integer id, TrainerRequestDTO trainerRequestDTO) {
        return null;
    }

    @Override
    public String deletedById(Integer id) {
        return "";
    }
}
