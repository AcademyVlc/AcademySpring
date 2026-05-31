package com.example.demo.repository.ristorante;

import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.entity.ristorante.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("""
            SELECT d
            FROM Dish d
            WHERE d.available = true
            """)
    List<Dish> findAvailableDish();

    @Query("""
            SELECT d
            FROM Dish d
            WHERE d.price < :price
            """)
    List<Dish> findUnderSpecificPriceDish(@Param("price") Double price);

    @Query("""
            SELECT d
            FROM Dish d
            WHERE LOWER(d.category.name) = LOWER(:categoryName)
            """)
    List<Dish> findDishByCategoryName(@Param("categoryName") String categoryName);
}
