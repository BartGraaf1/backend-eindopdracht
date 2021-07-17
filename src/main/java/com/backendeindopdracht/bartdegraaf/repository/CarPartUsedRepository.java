package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.CarPartStock;
import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;
import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarPartUsedRepository extends JpaRepository<CarPartUsed, Long> {
    CarPartUsed getById(Long id);

    List<CarPartUsed> getByRepairActionId(Long id);
}
