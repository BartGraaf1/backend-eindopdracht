package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;

import java.util.List;

public interface CarPartUsedService {

    List<CarPartUsed> getCarPartUseds();

    CarPartUsed getCarPartUsed(Long id);

    CarPartUsed saveCarPartUsed(CarPartUsed carPartUsed, Long carPartStockId, Long repairActionId);

    CarPartUsed updateCarPartUsed(CarPartUsed toCarPartUsed, Long carPartStockId, Long repairActionId);

    void deleteCarPartUsed(Long id);

    List<CarPartUsed> getCarForCarPartUsed(Long carPartUsedId);

}
