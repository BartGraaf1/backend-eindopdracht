package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.CarIssue;

import java.util.List;

public interface CarIssueService {
    List<CarIssue> getCarIssuesForCustomer(Long customerId);

    List<CarIssue> findCarIssuesByLicense(String query);

    List<CarIssue> findCarIssuesByType(String type);

    CarIssue saveCarIssue(CarIssue toCarIssue, Long customerId);

    CarIssue updateCarIssue(CarIssue carIssue, Long customerId);

    void deleteCarIssue(Long id);

    List<CarIssue> getCarIssues();

    CarIssue getCarIssue(Long id);
}
