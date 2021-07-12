package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarIssueRepository extends JpaRepository<CarIssue, Long> {
//    List<CarIssue> findByCarIssue(CarIssue carIssue);
//
//    List<CarIssue> findByCarIssueByCarId(Long id);
}
