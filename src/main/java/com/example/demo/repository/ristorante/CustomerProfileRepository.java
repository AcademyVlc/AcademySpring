package com.example.demo.repository.ristorante;

import com.example.demo.entity.ristorante.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Integer> {
}
