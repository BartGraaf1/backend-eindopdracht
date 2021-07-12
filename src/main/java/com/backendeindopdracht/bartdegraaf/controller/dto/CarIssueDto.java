package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CarIssueDto {
    public Long id;
    public String issueDescription;

    @JsonSerialize
    CarDto car;

    public static CarIssueDto fromCarIssue(CarIssue carIssue) {
        if (carIssue == null) return null;

        var dto = new CarIssueDto();
        dto.id = carIssue.getId();
        dto.issueDescription = carIssue.getIssueDescription();
        dto.car = CarDto.fromCar(carIssue.getCar());
        return dto;
    }
}
