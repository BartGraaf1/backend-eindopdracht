package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class RepairActionDto {
    public Long id;
    public String description;
    public Double cost;

    @JsonSerialize
    RepairEventDto repairEventDto;

    public static RepairActionDto fromRepairAction(RepairAction repairAction) {
        if (repairAction == null) return null;

        var dto = new RepairActionDto();
        dto.id = repairAction.getId();
        dto.description = repairAction.getDescription();
        dto.cost = repairAction.getCost();
        dto.repairEventDto = RepairEventDto.fromRepairEvent(repairAction.getRepairEvent());
        return dto;
    }
}
