package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.CarDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.CarInputDto;
import com.backendeindopdracht.bartdegraaf.exceptions.BadRequestException;
import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.service.CarService;
import com.backendeindopdracht.bartdegraaf.utils.LicenseCheckerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("cars")
public class    CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getCars(@RequestParam(value = "query", required = false, defaultValue = "") String query,
                                @RequestParam(value = "type", required = false) String type) {
        var dtos = new ArrayList<CarDto>();

        List<Car> cars;
        if (type == null) {
            cars = carService.findCarsByLicense(query);
        } else {
            cars = carService.findCarsByType(type);
        }

        for (Car car : cars) {
            dtos.add(CarDto.fromCar(car));
        }

        return dtos;
    }

    @PostMapping
    public CarDto saveCar(@RequestBody CarInputDto dto) {
        dto.licensePlate  = dto.licensePlate.toUpperCase(Locale.ROOT);
        LicenseCheckerUtil licensePlate = new LicenseCheckerUtil();
        licensePlate.setLicensePlate(dto.licensePlate);

        if (!licensePlate.isValidLicense()) {
            throw new BadRequestException();
        } else {
            var car = carService.saveCar(dto.toCar(), dto.customerId);
            return CarDto.fromCar(car);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
    }

//    @PutMapping("/{id}")
//    public CarDto updateCar(@PathVariable("id") long id, @RequestBody CarInputDto dto) {
//        var car = carService.updateCar(dto.toCar(), id);;
//        return CarDto.fromCar(car);
//    }

    @PutMapping("")
    public ResponseEntity<Object> updateCar(@RequestBody Car car) {
        carService.updateCar(car);
        return ResponseEntity.noContent().build();
    }
}
