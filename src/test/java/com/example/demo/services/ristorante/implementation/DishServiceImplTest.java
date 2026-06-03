package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.entity.ristorante.Dish;
import com.example.demo.mapper.ristorante.DishMapper;
import com.example.demo.repository.ristorante.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DishServiceImplTest {

    @Mock
    private DishRepository dishRepository;

    @Mock
    private DishMapper dishMapper;

    @InjectMocks
    private DishServiceImpl service;

    @Test
    void findAll() {
        when(dishRepository.findAll()).thenReturn(new ArrayList<>());
        when(dishMapper.entityToResponseDTO(anyList())).thenReturn(new ArrayList<>());
        List<DishResponseDTO> all = service.findAll();
        assertEquals(new ArrayList<>(), all);
    }

    @Test
    void findById() {
        Dish dish = new Dish();
        when(dishRepository.findById(anyInt())).thenReturn(Optional.of(dish));
        DishResponseDTO value = new DishResponseDTO();
        value.setName("Crotalo al forno");
        when(dishMapper.entityToResponseDTO(dish)).thenReturn(value);
        DishResponseDTO byId = service.findById(1);
        assertEquals("Crotalo al forno", byId.getName());
        when(dishRepository.findById(anyInt())).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, ()-> service.findById(1));
    }

//    @Test
//    void save() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void deletedById() {
//    }
//
//    @Test
//    void findAvailableDish() {
//    }
//
//    @Test
//    void findUnderSpecificPriceDish() {
//    }
//
//    @Test
//    void findDishByCategoryName() {
//    }
//
//    @Test
//    void findDishByChefName() {
//    }
//
//    @Test
//    void findAvailableDishByCategoryName() {
//    }
//
//    @Test
//    void findDishByRangePrice() {
//    }
//
//    @Test
//    void globalSearch() {
//    }
//
//    @Test
//    void countDishGroupingByCategory() {
//    }
}