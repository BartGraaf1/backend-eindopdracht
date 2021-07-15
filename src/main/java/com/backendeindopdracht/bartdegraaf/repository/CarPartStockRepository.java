package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.CarPartStock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarPartStockRepository extends JpaRepository<CarPartStock, Long> {
    CarPartStock getById(Long id);
}
