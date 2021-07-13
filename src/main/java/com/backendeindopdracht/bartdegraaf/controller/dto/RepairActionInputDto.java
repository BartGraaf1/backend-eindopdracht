package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.RepairAction;

public class RepairActionInputDto {
    public Long id;
    public String description;
    public Double cost;

    public Long repairEventId;

    public RepairAction toRepairAction() {
        var repairAction = new RepairAction();
        repairAction.setId(id);
        repairAction.setDescription(description);
        repairAction.setCost(cost);
        return repairAction;
    }
}
