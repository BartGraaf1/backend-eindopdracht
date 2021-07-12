package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.Car;

public class CarInputDto {
    public Long carId;
    public String licensePlate;
    public String type;
    public Long customerId;

    public Car toCar() {
        var car = new Car();
        car.setId(carId);
        car.setLicensePlate(licensePlate);
        car.setType(type);
        return car;
    }
}
