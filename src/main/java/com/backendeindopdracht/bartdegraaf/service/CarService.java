package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.Car;

import java.time.LocalDateTime;
import java.util.List;

public interface CarService {
    List<Car> getCarsBetweenDates(LocalDateTime start, LocalDateTime end);

    List<Car> getCarsForBoat(Long boatId);

    List<Car> getCarsForCustomer(Long customerId);

    void planCar(Long boatId, Long customerId, LocalDateTime plannedStartTime, LocalDateTime plannedEndTime);

    Car completeCar(Long bookingId, LocalDateTime actualStartTime, LocalDateTime actualEndTime);
}
