package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairEventRepository extends JpaRepository<RepairEvent, Long> {
    RepairEvent getById(Long id);
}
