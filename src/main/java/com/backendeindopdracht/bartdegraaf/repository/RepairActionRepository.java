package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairActionRepository extends JpaRepository<RepairAction, Long> {
    RepairAction getById(Long id);

    List<RepairAction> getByRepairEventId(Long id);
}
