package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.CarPartStock;

public class CarPartStockDto {
    public Long id;
    public Double price;
    public Long stock;
    public String description;

    public static CarPartStockDto fromCarPartStock(CarPartStock carPartStock) {
        var dto = new CarPartStockDto();
        dto.id = carPartStock.getId();
        dto.price = carPartStock.getPrice();
        dto.stock = carPartStock.getStock();
        dto.description = carPartStock.getDescription();
        return dto;
    }
}
