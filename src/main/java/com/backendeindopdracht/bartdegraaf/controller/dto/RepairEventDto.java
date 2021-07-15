package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RepairEventDto {
    public Long id;
    public String comment;
    public LocalDateTime dateOfEvent;
    public Boolean isRoutineService;
    public Boolean isEventPayed;
    public Boolean isEventApproved;

    @JsonSerialize
    CarDto car;

    public static RepairEventDto fromRepairEvent(RepairEvent repairEvent) {
        if (repairEvent == null) return null;

        var dto = new RepairEventDto();
        dto.id = repairEvent.getId();
        dto.comment = repairEvent.getComment();
        dto.dateOfEvent = repairEvent.getDateOfEvent();
        dto.isRoutineService = repairEvent.getRoutineService();
        dto.isEventPayed = repairEvent.getIsEventPayed();
        dto.isEventApproved = repairEvent.getIsEventApproved();
        dto.car = CarDto.fromCar(repairEvent.getCar());
        return dto;
    }
}
