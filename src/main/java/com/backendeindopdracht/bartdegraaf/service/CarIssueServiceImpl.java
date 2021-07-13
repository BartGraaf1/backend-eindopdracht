package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import com.backendeindopdracht.bartdegraaf.repository.CarIssueRepository;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarIssueServiceImpl implements CarIssueService {
    private final CarIssueRepository carIssueRepository;
    private final CarRepository carRepository;

    @Autowired
    public CarIssueServiceImpl(CarIssueRepository carIssueRepository, CarRepository carRepository) {
        this.carIssueRepository = carIssueRepository;
        this.carRepository = carRepository;
    }

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

    @Override
    public List<CarIssue> getCarIssues() {
        return carIssueRepository.findAll();
    }

    @Override
    public CarIssue getCarIssue(Long id) {
        Optional<CarIssue> optionalCarIssue = carIssueRepository.findById(id);
        if(optionalCarIssue.isPresent()) {
            return carIssueRepository.getById(id);
        }else{
            throw new RecordNotFoundException("Car issue does not exist");
        }
    }
}
