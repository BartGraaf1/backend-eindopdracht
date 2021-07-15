package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.CarPartStock;
import com.backendeindopdracht.bartdegraaf.repository.CarPartStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarPartStockServiceImpl implements CarPartStockService {
    private CarPartStockRepository repository;

    @Autowired
    public CarPartStockServiceImpl(CarPartStockRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CarPartStock> getCarPartStocks() {
        return repository.findAll();
    }

    @Override
    public CarPartStock getCarPartStock(Long id) {
        return repository.getById(id);
    }

    @Override
    public CarPartStock saveCarPartStock(CarPartStock carPartStock) {
        return repository.save(carPartStock);
    }

    @Override
    public void deleteCarPartStock(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CarPartStock> getCarPartStockForCar(Long carId) {
        return null;
    }

    @Override
    public CarPartStock updateCarPartStock(CarPartStock carPartStock) {
        Optional<CarPartStock> optionalCarPartStock = repository.findById(carPartStock.getId());
        if(optionalCarPartStock.isPresent()) {
            return repository.save(carPartStock);
        } else {
            throw new RecordNotFoundException("CarPartStock does not exist");
        }
    }

    @Override
    public List<CarPartStock> getCarForCarPartStock(Long carPartStockId) {
        return null;
    }
}
