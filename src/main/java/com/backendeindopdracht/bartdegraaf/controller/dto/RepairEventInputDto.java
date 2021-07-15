package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.RepairEvent;

import java.time.LocalDateTime;

public class RepairEventInputDto {
    public Long id;
    public String comment;
    public LocalDateTime dateOfEvent;
    public Boolean isRoutineService;
    public Boolean isEventPayed;
    public Boolean isEventApproved;

    public Long carId;

    public RepairEvent toRepairEvent() {
        var repairEvent = new RepairEvent();
        repairEvent.setId(id);
        repairEvent.setComment(comment);
        repairEvent.setDateOfEvent(dateOfEvent);
        repairEvent.setRoutineService(isRoutineService);
        repairEvent.setIsEventPayed(isEventPayed);
        repairEvent.setIsEventApproved(isEventApproved);
        return repairEvent;
    }
}
