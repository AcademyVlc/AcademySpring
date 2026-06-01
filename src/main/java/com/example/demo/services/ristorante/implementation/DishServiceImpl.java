package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.DishRequestDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.entity.ristorante.Dish;
import com.example.demo.mapper.ristorante.DishMapper;
import com.example.demo.repository.ristorante.DishRepository;
import com.example.demo.services.ristorante.abstraction.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Override
    public List<DishResponseDTO> findAll() {
        List<Dish> dishes = dishRepository.findAll();
        return dishMapper.entityToResponseDTO(dishes);
    }

    @Override
    public DishResponseDTO findById(Integer id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new RuntimeException("Dish not founded"));
        return dishMapper.entityToResponseDTO(dish);
    }

    @Override
    public DishResponseDTO save(DishRequestDTO dishRequestDTO) {
        Dish dish = dishMapper.requestDTOToEntity(dishRequestDTO);
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.entityToResponseDTO(savedDish);
    }

    @Override
    public DishResponseDTO update(Integer id, DishRequestDTO dishRequestDTO) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new RuntimeException("Dish not founded"));

        dish.setName(dishRequestDTO.getName());
        dish.setPrice(dishRequestDTO.getPrice());
        dish.setAvailable(dishRequestDTO.isAvailable());

        Dish savedDish = dishRepository.save(dish);

        return dishMapper.entityToResponseDTO(savedDish);
    }

    @Override
    public String deletedById(Integer id) {

        if (!dishRepository.existsById(id)) {
            throw new RuntimeException("Dish not founded");
        }

        dishRepository.deleteById(id);
        return "Deleted Dish with id - " + id;
    }

    @Override
    public List<DishResponseDTO> findAvailableDish() {
        List<Dish> availableDish = dishRepository.findAvailableDish();
        return dishMapper.entityToResponseDTO(availableDish);
    }

    @Override
    public List<DishResponseDTO> findUnderSpecificPriceDish(Double price) {
        List<Dish> dishes = dishRepository.findUnderSpecificPriceDish(price);
        return dishMapper.entityToResponseDTO(dishes);
    }

    @Override
    public List<DishResponseDTO> findDishByCategoryName(String categoryName) {
        List<Dish> dishes = dishRepository.findDishByCategoryName(categoryName);
        return dishMapper.entityToResponseDTO(dishes);
    }

    @Override
    public List<DishResponseDTO> findDishByChefName(String chefName) {
        List<Dish> dishes = dishRepository.findDishByChefName(chefName);
        return dishMapper.entityToResponseDTO(dishes);
    }

    @Override
    public List<DishResponseDTO> findAvailableDishByCategoryName(String categoryName) {
        List<Dish> dishes = dishRepository.findAvailableDishByCategoryName(categoryName);
        return dishMapper.entityToResponseDTO(dishes);
    }

    @Override
    public List<DishResponseDTO> findDishByRangePrice(Double min, Double max) {
        List<Dish> dishes = dishRepository.findDishByRangePrice(min, max);
        return dishMapper.entityToResponseDTO(dishes);
    }

    @Override
    public List<DishResponseDTO> globalSearch(String name) {
        List<Dish> dishes = dishRepository.globalSearch(name);
        return dishMapper.entityToResponseDTO(dishes);
    }

    @Override
    public List<Object[]> countDishGroupingByCategory() {
        List<Object[]> dishes = dishRepository.countDishGroupingByCategory();
        return dishes;
    }

}
