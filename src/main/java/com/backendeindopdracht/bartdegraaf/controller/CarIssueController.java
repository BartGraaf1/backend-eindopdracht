package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.CarIssueDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.CarIssueInputDto;
import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import com.backendeindopdracht.bartdegraaf.service.CarIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("carIssues")
public class CarIssueController {
    private final CarIssueService carIssueService;

    @Autowired
    public CarIssueController(CarIssueService carIssueService) {
        this.carIssueService = carIssueService;
    }

    @GetMapping
    public List<CarIssueDto> getCarissue() {
        var dtos = new ArrayList<CarIssueDto>();
        var carIssues = carIssueService.getCarIssues();

        for (CarIssue carIssue : carIssues) {
            dtos.add(CarIssueDto.fromCarIssue(carIssue));
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public CarIssueDto getCustomer(@PathVariable("id") Long id) {
        var carIssues= carIssueService.getCarIssue(id);
        return CarIssueDto.fromCarIssue(carIssues);
    }

    @PostMapping
    public CarIssueDto saveCarIssue(@RequestBody CarIssueInputDto dto) {
        var carIssue = carIssueService.saveCarIssue(dto.toCarIssue(), dto.carId);
        return CarIssueDto.fromCarIssue(carIssue);
    }

    @DeleteMapping("/{id}")
    public void deleteCarIssue(@PathVariable("id") Long id) {
        carIssueService.deleteCarIssue(id);
    }


    @PutMapping("")
    public CarIssueDto updateCarIssue(@RequestBody CarIssueInputDto dto) {
        var carIssue = carIssueService.updateCarIssue(dto.toCarIssue(), dto.carId);
        return CarIssueDto.fromCarIssue(carIssue);
    }
}
