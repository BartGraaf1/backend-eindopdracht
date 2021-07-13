package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarIssueRepository extends JpaRepository<CarIssue, Long> {
    CarIssue getById(Long id);
}
