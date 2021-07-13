package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairActionRepository extends JpaRepository<RepairAction, Long> {
    RepairAction getById(Long id);
}
