package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.ChefRequestDTO;
import com.example.demo.dto.ristorante.responsedto.ChefResponseDTO;
import com.example.demo.entity.ristorante.Chef;
import com.example.demo.mapper.ristorante.ChefMapper;
import com.example.demo.repository.ristorante.ChefRepository;
import com.example.demo.services.exceptions.ChefNotFoundedException;
import com.example.demo.services.ristorante.abstraction.ChefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final ChefMapper chefMapper;

    @Override
    public List<ChefResponseDTO> findAll() {
        List<Chef> chefs = chefRepository.findAll();
        return chefMapper.entityToResponseDTO(chefs);
    }

    @Override
    public ChefResponseDTO findById(Integer id) {
        Chef chef = chefRepository.findById(id).orElseThrow(() -> new RuntimeException("Not founded chef with id - " + id));
        return chefMapper.entityToResponseDTO(chef);
    }

    @Override
    public ChefResponseDTO save(ChefRequestDTO chefRequestDTO) {
        Chef chef = chefMapper.requestDTOToEntity(chefRequestDTO);

        Chef savedChef = chefRepository.save(chef);

        return chefMapper.entityToResponseDTO(savedChef);
    }

    @Override
    public String deletedById(Integer id) {

        if (!chefRepository.existsById(id)) {
            throw new ChefNotFoundedException("Chef not founded with id - " + id);
        }
        chefRepository.deleteById(id);
        return "Deleted chef with id - " + id;
    }
}
