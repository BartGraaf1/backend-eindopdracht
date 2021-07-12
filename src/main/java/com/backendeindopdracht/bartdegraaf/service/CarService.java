package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getCarsForCustomer(Long customerId);

    List<Car> findCarsByLicense(String query);

    List<Car> findCarsByType(String type);

    Car saveCar(Car toCar, Long customerId);

    void updateCar(Car car);

    void deleteCar(Long id);

}
