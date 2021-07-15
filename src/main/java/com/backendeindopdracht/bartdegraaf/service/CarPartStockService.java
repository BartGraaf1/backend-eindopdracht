package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.CarPartStock;

import java.util.List;

public interface CarPartStockService {

    List<CarPartStock> getCarPartStocks();

    CarPartStock getCarPartStock(Long id);

    CarPartStock saveCarPartStock(CarPartStock carPartStock);

    CarPartStock updateCarPartStock(CarPartStock toCarPartStock);

    void deleteCarPartStock(Long id);

    List<CarPartStock> getCarPartStockForCar(Long carId);

    List<CarPartStock> getCarForCarPartStock(Long carPartStockId);

}
