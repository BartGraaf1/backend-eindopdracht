package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CarDto {
    public Long id;
    public String type;
    public String licensePlate;

    @JsonSerialize
    CustomerDto customer;

    public static CarDto fromCar(Car car) {
        if (car == null) return null;

        var dto = new CarDto();
        dto.id = car.getId();
        dto.type = car.getType();
        dto.licensePlate = car.getLicensePlate();
        dto.customer = CustomerDto.fromCustomer(car.getCustomer());
        return dto;
    }
}
