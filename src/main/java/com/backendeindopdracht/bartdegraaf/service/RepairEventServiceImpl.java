package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.backendeindopdracht.bartdegraaf.repository.RepairEventRepository;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairEventServiceImpl implements RepairEventService {
    private final RepairEventRepository repairEventRepository;
    private final CarRepository carRepository;

    @Autowired
    public RepairEventServiceImpl(RepairEventRepository repairEventRepository, CarRepository carRepository) {
        this.repairEventRepository = repairEventRepository;
        this.carRepository = carRepository;
    }

    @Override
    public RepairEvent saveRepairEvent(RepairEvent toRepairEvent, Long carId) {
        var optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException();
        }

        var car = optionalCar.get();
        var repairEvent = new RepairEvent();
        repairEvent.setId(toRepairEvent.getId());
        repairEvent.setCar(car);
        repairEvent.setComment(toRepairEvent.getComment());
        repairEvent.setDateOfEvent(toRepairEvent.getDateOfEvent());
        repairEvent.setRoutineService(toRepairEvent.getRoutineService());
        repairEvent.setIsEventPayed(toRepairEvent.getIsEventPayed());
        repairEvent.setIsEventApproved(toRepairEvent.getIsEventApproved());

        return repairEventRepository.save(repairEvent);
    }


    @Override
    public RepairEvent updateRepairEvent(RepairEvent toRepairEvent, Long carId) {
        var optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException();
        }

        var car = optionalCar.get();
        var repairEvent = new RepairEvent();
        repairEvent.setId(toRepairEvent.getId());
        repairEvent.setCar(car);
        repairEvent.setComment(toRepairEvent.getComment());
        repairEvent.setDateOfEvent(toRepairEvent.getDateOfEvent());
        repairEvent.setRoutineService(toRepairEvent.getRoutineService());
        repairEvent.setIsEventPayed(toRepairEvent.getIsEventPayed());
        repairEvent.setIsEventApproved(toRepairEvent.getIsEventApproved());

        Optional<RepairEvent> optionalRepairEventToUpdate = repairEventRepository.findById(repairEvent.getId());
        if(optionalRepairEventToUpdate.isPresent()) {
            return repairEventRepository.save(repairEvent);
        } else {
            throw new RecordNotFoundException("Repair event does not exist");
        }
    }

    @Override
    public void deleteRepairEvent(Long id) {
        repairEventRepository.deleteById(id);
    }

    @Override
    public List<RepairEvent> getRepairEvents() {
        return repairEventRepository.findAll();
    }

    @Override
    public RepairEvent getRepairEvent(Long id) {
        Optional<RepairEvent> optionalRepairEvent = repairEventRepository.findById(id);
        if(optionalRepairEvent.isPresent()) {
            return repairEventRepository.getById(id);
        }else{
            throw new RecordNotFoundException("Car issue does not exist");
        }
    }
}
