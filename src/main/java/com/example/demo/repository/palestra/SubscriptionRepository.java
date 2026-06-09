package com.example.demo.repository.palestra;


import com.example.demo.entity.palestra.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    // Calcolare incasso totale da abbonamenti attivi -> COALESCE = Se il valore è NULL, usa quello che ti do io.
    @Query("""
        SELECT COALESCE(SUM(s.price), 0)
        FROM Subscription s
        WHERE s.endDate >= CURRENT_DATE
        """)
    BigDecimal calculateActiveSubscriptionsRevenue();

}
