package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.BadRequestException;
import com.backendeindopdracht.bartdegraaf.exceptions.DefaultExceptionWithMessage;
import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.repository.CustomerRepository;
import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        var optionalCustomer = customerRepository.findById(customerId);
        var optionalCar = carRepository.findByLicensePlate(toCar.getLicensePlate());


        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException();
        }

        if(!optionalCar.isEmpty()){
            throw new DefaultExceptionWithMessage("License plate is already in use");
        }

        var customer = optionalCustomer.get();
        var car = new Car();
        car.setCustomer(customer);
        car.setLicensePlate(toCar.getLicensePlate());
        car.setType(toCar.getType());

        return carRepository.save(car);
    }


    @Override
    public Car updateCar(Car toCar, Long customerId) {
        var optionalCustomer = customerRepository.findById(customerId);
        var optionalCar = carRepository.findByLicensePlate(toCar.getLicensePlate());

        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException();
        }

        if(!optionalCar.isEmpty() && !optionalCar.get(0).getId().equals(toCar.getId())){
            throw new DefaultExceptionWithMessage("License plate is already in use");
        }

        var customer = optionalCustomer.get();
        var car = new Car();
        car.setId(toCar.getId());
        car.setCustomer(customer);
        car.setLicensePlate(toCar.getLicensePlate());
        car.setType(toCar.getType());

        Optional<Car> optionalCarToUpdate = carRepository.findById(car.getId());
        if(optionalCarToUpdate.isPresent()) {
            return carRepository.save(car);
        } else {
            throw new RecordNotFoundException("Person does not exist");
        }
    }

    @Override
    public Car getCar(Long id) {
        return carRepository.getById(id);
    }


    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void uploadPapers(Long id, MultipartFile file) throws IOException {
        var optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            var car = optionalCar.get();
            car.setPapers(file.getBytes());
            carRepository.save(car);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public byte[] getPapers(Long id) {
        var optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            return optionalCar.get().getPapers();
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }
}
