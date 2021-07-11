package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.repository.CustomerRepository;
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
    public List<Car> findCarsByLicense(String query) {
        return null;
    }

    @Override
    public List<Car> findCarsByType(String type) {
        return null;
    }


    @Override
    public Car saveCar(Car toCar, Long customerId) {
        System.out.println("Customer id: " + customerId);
        var optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException();
        }

        var customer = optionalCustomer.get();

        //TODO: Validate license is empty

        var car = new Car();
        car.setCustomer(customer);
        car.setLicensePlate(toCar.getLicensePlate());
        car.setType(toCar.getType());

        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
