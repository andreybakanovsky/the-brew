package com.flight.thebrew.core.repository;

import com.flight.thebrew.core.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findByCost(BigDecimal cost);
}
