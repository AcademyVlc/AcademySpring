package com.example.demo.repository.ristorante;

import com.example.demo.entity.ristorante.OrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRestaurantRepository extends JpaRepository<OrderRestaurant, Integer> {
}
