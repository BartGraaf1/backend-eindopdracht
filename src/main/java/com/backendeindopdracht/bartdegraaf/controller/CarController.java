package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.CarDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.CarInputDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.CarPartStockDto;
import com.backendeindopdracht.bartdegraaf.exceptions.BadRequestException;
import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.CarPartStock;
import com.backendeindopdracht.bartdegraaf.service.CarService;
import com.backendeindopdracht.bartdegraaf.utils.LicenseCheckerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getCarPartStocks() {
        var dtos = new ArrayList<CarDto>();
        var cars = carService.getCars();

        for (Car car : cars) {
            dtos.add(CarDto.fromCar(car));
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable("id") Long id) {
        var car = carService.getCar(id);
        return CarDto.fromCar(car);
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


    @PutMapping("")
    public CarDto updateCar(@RequestBody CarInputDto dto) {
        dto.licensePlate  = dto.licensePlate.toUpperCase(Locale.ROOT);
        LicenseCheckerUtil licensePlate = new LicenseCheckerUtil();
        licensePlate.setLicensePlate(dto.licensePlate);

        if (!licensePlate.isValidLicense()) {
            throw new BadRequestException();
        } else {
            var car = carService.updateCar(dto.toCar(), dto.customerId);
            return CarDto.fromCar(car);
        }
    }

    @GetMapping("/{id}/papers")
    public ResponseEntity<byte[]> getPapers(@PathVariable("id") Long id) {
        var licenseBytes = carService.getPapers(id);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"papers.pdf\"")
                .body(licenseBytes);
    }

    @PostMapping("/{id}/papers")
    public void uploadPapers(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Nu moet he wel het bespand versturen");
        System.out.println("Car id: " + id);
        if (file.getContentType() == null || !file.getContentType().equals("application/pdf")) {
            throw new BadRequestException();
        }
        carService.uploadPapers(id, file);
    }
}
