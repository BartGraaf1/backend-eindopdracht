package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;
import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CarPartUsedDto {
    public Long id;
    public Long amountUsed;

    @JsonSerialize
    RepairActionDto repairAction;

    @JsonSerialize
    CarPartStockDto carPartStock;

    public static CarPartUsedDto fromCarPartUsed(CarPartUsed carPartUsed) {
        if (carPartUsed == null) return null;

        var dto = new CarPartUsedDto();
        dto.id = carPartUsed.getId();
        dto.amountUsed = carPartUsed.getAmountUsed();
        dto.carPartStock = CarPartStockDto.fromCarPartStock(carPartUsed.getCarPartStock());
        dto.repairAction = RepairActionDto.fromRepairAction(carPartUsed.getRepairEvent());
        return dto;
    }
}
