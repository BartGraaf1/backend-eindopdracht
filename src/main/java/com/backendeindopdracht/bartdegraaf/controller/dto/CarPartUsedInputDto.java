package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;

public class CarPartUsedInputDto {
    public Long id;
    public Long amountUsed;

    public Long carPartStockId;
    public Long repairActionId;

    public CarPartUsed toCarPartUsed() {
        var carPartUsed = new CarPartUsed();
        carPartUsed.setId(id);
        carPartUsed.setAmountUsed(amountUsed);
        return carPartUsed;
    }
}
