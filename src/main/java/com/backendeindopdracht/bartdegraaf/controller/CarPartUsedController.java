package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.CarPartUsedDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.CarPartUsedInputDto;
import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;
import com.backendeindopdracht.bartdegraaf.service.CarPartUsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("carPartUsed")
public class CarPartUsedController {
    private final CarPartUsedService CarPartUsedService;

    @Autowired
    public CarPartUsedController(CarPartUsedService CarPartUsedService) {
        this.CarPartUsedService = CarPartUsedService;
    }

    @GetMapping
    public List<CarPartUsedDto> getCarPartUsed() {
        var dtos = new ArrayList<CarPartUsedDto>();
        var carPartUsed = CarPartUsedService.getCarPartUseds();

        for (CarPartUsed CarPartUsed : carPartUsed) {
            dtos.add(CarPartUsedDto.fromCarPartUsed(CarPartUsed));
        }
        return dtos;
    }


    @GetMapping("/{id}")
    public CarPartUsedDto getCarPartUsed(@PathVariable("id") Long id) {
        var CarPartUsed = CarPartUsedService.getCarPartUsed(id);
        return CarPartUsedDto.fromCarPartUsed(CarPartUsed);
    }

    @PostMapping
    public CarPartUsedDto saveCarPartUsed(@RequestBody CarPartUsedInputDto dto) {
        var CarPartUsed = CarPartUsedService.saveCarPartUsed(dto.toCarPartUsed(), dto.carPartStockId, dto.repairActionId);
        return CarPartUsedDto.fromCarPartUsed(CarPartUsed);
    }

    @PutMapping
    public CarPartUsedDto updateCarPartUsed(@RequestBody CarPartUsedInputDto dto) {
        var CarPartUsed = CarPartUsedService.updateCarPartUsed(dto.toCarPartUsed(), dto.carPartStockId, dto.repairActionId);
        return CarPartUsedDto.fromCarPartUsed(CarPartUsed);
    }

    @DeleteMapping("/{id}")
    public void deleteCarPartUsed(@PathVariable("id") Long id) {
        CarPartUsedService.deleteCarPartUsed(id);
    }
}
