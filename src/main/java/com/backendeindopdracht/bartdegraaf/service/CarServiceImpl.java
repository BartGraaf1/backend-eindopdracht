package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.repository.CustomerRepository;
import com.backendeindopdracht.bartdegraaf.exceptions.BadRequestException;
import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Car> getCarsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return carRepository.findByPlannedStartTimeBetween(start, end);
    }

    @Override
    public List<Car> getCarsForBoat(Long boatId) {
        return null;
    }

    @Override
    public List<Car> getCarsForCustomer(Long customerId) {
        var optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            var customer = optionalCustomer.get();
            return carRepository.findByCustomer(customer);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void planCar(Long boatId, Long customerId, LocalDateTime plannedStartTime, LocalDateTime plannedEndTime) {

    }

    @Override
    public Car completeCar(Long bookingId, LocalDateTime actualStartTime, LocalDateTime actualEndTime) {
        return null;
    }
}
