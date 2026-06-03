package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.ChefRequestDTO;
import com.example.demo.dto.ristorante.responsedto.ChefResponseDTO;
import com.example.demo.entity.ristorante.Chef;
import com.example.demo.mapper.ristorante.ChefMapper;
import com.example.demo.repository.ristorante.ChefRepository;
import com.example.demo.services.exceptions.ChefNotFoundedException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChefServiceImplTest {

    @Mock
    private ChefRepository chefRepository;

    @Mock
    private ChefMapper chefMapper;

    @InjectMocks
    private ChefServiceImpl service;

    @Test
    void findAll() {
        List<Chef> chefs = new ArrayList<>();
        chefs.add(new Chef());

        List<ChefResponseDTO> dtos = new ArrayList<>();
        ChefResponseDTO dto = new ChefResponseDTO();
        dto.setName("bruno");
        dtos.add(dto);

        when(chefRepository.findAll()).thenReturn(chefs);
        when(chefMapper.entityToResponseDTO(chefs)).thenReturn(dtos);

        List<ChefResponseDTO> result = service.findAll();
        assertEquals(1, result.size());
        assertEquals("bruno", result.get(0).getName());

    }

    @Test
    void findById() {
        Integer id = 1;
        Chef chef = new Chef();

        ChefResponseDTO chefDTO = new ChefResponseDTO();
        chefDTO.setName("bruno");

        when(chefRepository.findById(id)).thenReturn(Optional.of(chef));
        when(chefMapper.entityToResponseDTO(chef)).thenReturn(chefDTO);

        ChefResponseDTO result = service.findById(id);
        assertEquals("bruno", result.getName());
    }

    @Test
    void save() {
        ChefRequestDTO chefRequestDTO = new ChefRequestDTO();
        chefRequestDTO.setName("bruno");
        Chef chef = new Chef();
        Chef savedChef = new Chef();
        ChefResponseDTO chefResponseDTO = new ChefResponseDTO();
        chefResponseDTO.setName("bruno");

        when(chefMapper.requestDTOToEntity(chefRequestDTO)).thenReturn(chef);
        when(chefRepository.save(chef)).thenReturn(savedChef);
        when(chefMapper.entityToResponseDTO(savedChef)).thenReturn(chefResponseDTO);

        ChefResponseDTO result = service.save(chefRequestDTO);
        assertEquals("bruno", result.getName());
    }

    @Test
    void update() {
        Integer id = 1;
        ChefRequestDTO chefRequestDTO = new ChefRequestDTO();
        chefRequestDTO.setName("bruno");

        Chef chef = new Chef();
        Chef updatedChef = new Chef();

        ChefResponseDTO chefResponseDTO = new ChefResponseDTO();
        chefResponseDTO.setName("bruno");

        when(chefRepository.findById(id)).thenReturn(Optional.of(chef));
        when(chefRepository.save(chef)).thenReturn(updatedChef);
        when(chefMapper.entityToResponseDTO(updatedChef)).thenReturn(chefResponseDTO);

        ChefResponseDTO result = service.update(id, chefRequestDTO);
        assertEquals("bruno", result.getName());
    }

    @Test
    void deletedById() {
        //Lasciare sempre perché non deve fare nulla
        doNothing().when(chefRepository).deleteById(anyInt());
        //1) Operazione andata a buon fine
        when(chefRepository.existsById(anyInt())).thenReturn(true);
        service.deletedById(52);
        //2) Chef non trovato
        when(chefRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(ChefNotFoundedException.class, () -> service.deletedById(52));

    }
}