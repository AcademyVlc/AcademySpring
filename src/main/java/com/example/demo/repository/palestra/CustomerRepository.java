package com.example.demo.repository.palestra;

import com.example.demo.entity.palestra.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Trovare clienti con abbonamento attivo
    @Query("""
            SELECT c
            FROM Customer c
            WHERE c.subscription.endDate >= CURRENT DATE 
            """)
    List<Customer> findCustomersWithActiveSubscription();

    // Trovare clienti iscritti a un corso tramite nome corso
    @Query("""
            SELECT c
            FROM Course c
            JOIN c.customers customer
            WHERE LOWER(c.name) = LOWER(:courseName) 
            """)
    List<Customer> findCustomersByCourseName(@Param("courseName") String courseName);




}
