package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.CarIssue;

public class CarIssueInputDto {
    public Long id;
    public String issueDescription;

    public Long carId;

    public CarIssue toCarIssue() {
        var carIssue = new CarIssue();
        carIssue.setId(id);
        carIssue.setIssueDescription(issueDescription);
        return carIssue;
    }
}
