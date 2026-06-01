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

    @Query("""
            SELECT d
            FROM Dish d
            WHERE LOWER(d.chef.name) LIKE LOWER(CONCAT('%', :chefName ,'%'))
            """)
    List<Dish> findDishByChefName(@Param("chefName") String chefName);

    @Query("""
            SELECT d
            FROM Dish d
            WHERE LOWER(d.category.name) LIKE LOWER(CONCAT('%', :categoryName ,'%'))
                        AND d.available = true
            """)
    List<Dish> findAvailableDishByCategoryName(@Param("categoryName") String categoryName);

    @Query("""
            SELECT d
            FROM Dish d
            WHERE d.price BETWEEN :min AND :max
            """)
    List<Dish> findDishByRangePrice(@Param("min") Double min, @Param("max") Double max);

    @Query("""
            SELECT d
            FROM Dish d
            WHERE LOWER(d.name) LIKE LOWER(CONCAT('%',:name, '%'))
            OR LOWER(d.category.name) LIKE LOWER(CONCAT('%',:name, '%'))
            OR LOWER(d.chef.name) LIKE LOWER(CONCAT('%',:name, '%'))
            """)
    List<Dish> globalSearch(@Param("name") String name);

    @Query("""
            SELECT d.category.name, COUNT(*)
            FROM Dish d
            WHERE d.available = true
            GROUP BY d.category.name
            """)
    List<Object[]> countDishGroupingByCategory();
}
