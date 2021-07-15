package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.CarPartStock;
import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarPartUsedRepository extends JpaRepository<CarPartUsed, Long> {
    CarPartUsed getById(Long id);
}
