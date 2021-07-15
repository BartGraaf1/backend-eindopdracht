package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.CarPartStockDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.CarPartStockInputDto;
import com.backendeindopdracht.bartdegraaf.model.CarPartStock;
import com.backendeindopdracht.bartdegraaf.service.CarPartStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("carPartStocks")
public class CarPartStockController {
    private final CarPartStockService carPartStockService;

    @Autowired
    public CarPartStockController(CarPartStockService carPartStockService) {
        this.carPartStockService = carPartStockService;
    }

    @GetMapping
    public List<CarPartStockDto> getCarPartStocks() {
        var dtos = new ArrayList<CarPartStockDto>();
        var carPartStocks = carPartStockService.getCarPartStocks();

        for (CarPartStock carPartStock : carPartStocks) {
            dtos.add(CarPartStockDto.fromCarPartStock(carPartStock));
        }
        return dtos;
    }


    @GetMapping("/{id}")
    public CarPartStockDto getCarPartStock(@PathVariable("id") Long id) {
        var carPartStock = carPartStockService.getCarPartStock(id);
        return CarPartStockDto.fromCarPartStock(carPartStock);
    }

    @PostMapping
    public CarPartStockDto saveCarPartStock(@RequestBody CarPartStockInputDto dto) {
        var carPartStock = carPartStockService.saveCarPartStock(dto.toCarPartStock());
        return CarPartStockDto.fromCarPartStock(carPartStock);
    }

    @PutMapping
    public CarPartStockDto updateCarPartStock(@RequestBody CarPartStockInputDto dto) {
        var carPartStock = carPartStockService.updateCarPartStock(dto.toCarPartStock());
        return CarPartStockDto.fromCarPartStock(carPartStock);
    }

    @DeleteMapping("/{id}")
    public void deleteCarPartStock(@PathVariable("id") Long id) {
        carPartStockService.deleteCarPartStock(id);
    }
}
