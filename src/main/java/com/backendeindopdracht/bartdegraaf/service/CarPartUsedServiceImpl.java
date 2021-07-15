package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;
import com.backendeindopdracht.bartdegraaf.repository.CarPartStockRepository;
import com.backendeindopdracht.bartdegraaf.repository.CarPartUsedRepository;
import com.backendeindopdracht.bartdegraaf.repository.RepairActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarPartUsedServiceImpl implements CarPartUsedService {
    private CarPartUsedRepository repository;
    private CarPartStockRepository carPartStockRepository;
    private RepairActionRepository repairActionRepository;

    @Autowired
    public CarPartUsedServiceImpl(CarPartUsedRepository repository, CarPartStockRepository carPartStockRepository, RepairActionRepository repairActionRepository) {
        this.repository = repository;
        this.carPartStockRepository = carPartStockRepository;
        this.repairActionRepository = repairActionRepository;
    }

    @Override
    public List<CarPartUsed> getCarPartUseds() {
        return repository.findAll();
    }

    @Override
    public CarPartUsed getCarPartUsed(Long id) {
        return repository.getById(id);
    }

    @Override
    public CarPartUsed saveCarPartUsed(CarPartUsed carPartUsed, Long carPartStockId, Long repairActionId) {
        var optionCarPartStock = carPartStockRepository.findById(carPartStockId);
        var optionRepairAction = repairActionRepository.findById(repairActionId);

        if (optionCarPartStock.isEmpty() || optionRepairAction.isEmpty()) {
            throw new NotFoundException();
        }

        var carPartStock = optionCarPartStock.get();

        if(carPartUsed.getAmountUsed() > carPartStock.getStock()){
            throw new NotFoundException();
        }
        var newStock = carPartStock.getStock() - carPartUsed.getAmountUsed();
        carPartStock.setStock(newStock);

        var repairAction = optionRepairAction.get();
        var carPartUsedTemp = new CarPartUsed();
        carPartUsedTemp.setId(carPartStock.getId());
        carPartUsedTemp.setCarPartStock(carPartStock);
        carPartUsedTemp.setRepairAction(repairAction);
        carPartUsedTemp.setAmountUsed(carPartUsed.getAmountUsed());
        return repository.save(carPartUsedTemp);
    }

    @Override
    public void deleteCarPartUsed(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CarPartUsed updateCarPartUsed(CarPartUsed carPartUsed, Long carPartStockId, Long repairActionId) {
        Optional<CarPartUsed> optionalCarPartUsed = repository.findById(carPartUsed.getId());

        var optionCarPartStock = carPartStockRepository.findById(carPartStockId);
        var optionRepairAction = repairActionRepository.findById(repairActionId);

        if (optionCarPartStock.isEmpty() || optionRepairAction.isEmpty()) {
            throw new NotFoundException();
        }

        if(!optionalCarPartUsed.isPresent()) {
            throw new RecordNotFoundException("CarPartUsed does not exist");
        }

        var curCarPart = optionalCarPartUsed.get();
        var carPartStock = optionCarPartStock.get();
        var stockDifference = 0L;
        var newStock = 0L;
        if(carPartUsed.getAmountUsed() > curCarPart.getAmountUsed()){
            stockDifference = carPartUsed.getAmountUsed() - curCarPart.getAmountUsed();
            newStock = carPartStock.getStock() - stockDifference;
        }else if( curCarPart.getAmountUsed() > carPartUsed.getAmountUsed()){
            stockDifference = curCarPart.getAmountUsed() - carPartUsed.getAmountUsed();
            newStock = stockDifference + carPartStock.getStock();
        }


        if(newStock < 0){
            throw new NotFoundException();
        }

        carPartStock.setStock(newStock);
        var repairAction = optionRepairAction.get();
        var carPartUsedTemp = new CarPartUsed();
        carPartUsedTemp.setId(carPartUsed.getId());
        carPartUsedTemp.setCarPartStock(carPartStock);
        carPartUsedTemp.setRepairAction(repairAction);
        carPartUsedTemp.setAmountUsed(carPartUsed.getAmountUsed());
        return repository.save(carPartUsedTemp);
    }


    @Override
    public List<CarPartUsed> getCarForCarPartUsed(Long carPartUsedId) {
        return null;
    }
}
