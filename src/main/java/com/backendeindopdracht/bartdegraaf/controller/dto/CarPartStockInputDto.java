package com.backendeindopdracht.bartdegraaf.controller.dto;


import com.backendeindopdracht.bartdegraaf.model.CarPartStock;

public class CarPartStockInputDto {
    public Long id;
    public Double price;
    public Long stock;
    public String description;

    public CarPartStock toCarPartStock() {
        var carPartStock = new CarPartStock();
        carPartStock.setId(id);
        carPartStock.setPrice(price);
        carPartStock.setStock(stock);
        carPartStock.setDescription(description);
        return carPartStock;
    }
}
