package com.example.demo.services.ristorante.abstraction;

import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.entity.ristorante.Dish;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishService {

    List<DishResponseDTO> findAll();

    DishResponseDTO findById(Integer id);

    DishResponseDTO save(DishRequestDTO dishRequestDTO);

    DishResponseDTO update(Integer id, DishRequestDTO dishRequestDTO);

    String deletedById(Integer id);

    List<DishResponseDTO> findAvailableDish();

    List<DishResponseDTO> findUnderSpecificPriceDish(Double price);

    List<DishResponseDTO> findDishByCategoryName(String categoryName);

    List<DishResponseDTO> findDishByChefName(String chefName);

    List<DishResponseDTO> findAvailableDishByCategoryName(String categoryName);

    List<DishResponseDTO> findDishByRangePrice(Double min, Double max);

    List<DishResponseDTO> globalSearch(String name);

    List<Object[]> countDishGroupingByCategory();
}
