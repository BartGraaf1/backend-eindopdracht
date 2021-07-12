package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.BadRequestException;
import com.backendeindopdracht.bartdegraaf.exceptions.DefaultExceptionWithMessage;
import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import com.backendeindopdracht.bartdegraaf.repository.CarIssueRepository;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarIssueServiceImpl implements CarIssueService {
    private CarIssueRepository carIssueRepository;
    private CarRepository carRepository;

    @Autowired
    public CarIssueServiceImpl(CarIssueRepository carIssueRepository, CarRepository carRepository) {
        this.carIssueRepository = carIssueRepository;
        this.carRepository = carRepository;
    }

//    @Override
//    public List<CarIssue> getCarIssuesForCar(Long carId) {
//        var optionalCar = carRepository.findById(carId);
//
//        if (optionalCar.isPresent()) {
//            var car = optionalCar.get();
//            return carIssueRepository.findByCarIssueByCarId(car.getId());
//        } else {
//            throw new NotFoundException();
//        }
//    }


    @Override
    public List<CarIssue> getCarIssuesForCustomer(Long customerId) {
        return null;
    }

    @Override
    public List<CarIssue> findCarIssuesByLicense(String query) {
        return null;
    }

    @Override
    public List<CarIssue> findCarIssuesByType(String type) {
        return null;
    }

    @Override
    public CarIssue saveCarIssue(CarIssue toCarIssue, Long carId) {
        var optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException();
        }

        var car = optionalCar.get();
        var carIssue = new CarIssue();
        carIssue.setId(toCarIssue.getId());
        carIssue.setCar(car);
        carIssue.setIssueDescription(toCarIssue.getIssueDescription());

        return carIssueRepository.save(carIssue);
    }


    @Override
    public CarIssue updateCarIssue(CarIssue toCarIssue, Long carId) {
        var optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException();
        }

        var car = optionalCar.get();
        var carIssue = new CarIssue();
        carIssue.setId(toCarIssue.getId());
        carIssue.setCar(car);
        carIssue.setIssueDescription(toCarIssue.getIssueDescription());

        Optional<CarIssue> optionalCarIssueToUpdate = carIssueRepository.findById(carIssue.getId());
        if(optionalCarIssueToUpdate.isPresent()) {
            return carIssueRepository.save(carIssue);
        } else {
            throw new RecordNotFoundException("Car issue does not exist");
        }
    }

    @Override
    public void deleteCarIssue(Long id) {
        carIssueRepository.deleteById(id);
    }
}
