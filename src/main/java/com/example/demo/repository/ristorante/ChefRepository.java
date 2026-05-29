package com.example.demo.repository.ristorante;

import com.example.demo.entity.ristorante.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends JpaRepository<Chef, Integer> {
}
