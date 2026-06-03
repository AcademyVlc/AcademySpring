package com.example.demo.services.ristorante.implementation;

import com.example.demo.mapper.ristorante.ChefMapper;
import com.example.demo.repository.ristorante.ChefRepository;
import com.example.demo.services.exceptions.ChefNotFoundedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChefServiceImplTest {

    @Mock
    private ChefRepository chefRepository;

//    @Mock
//    private ChefMapper chefMapper;

    @InjectMocks
    private ChefServiceImpl service;

//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void save() {
//    }
//
//    @Test
//    void update() {
//    }

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