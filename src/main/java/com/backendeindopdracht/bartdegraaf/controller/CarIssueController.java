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
    public List<CarIssueDto> getCarIssues(@RequestParam(value = "query", required = false, defaultValue = "") String query,
                                @RequestParam(value = "type", required = false) String type) {
        var dtos = new ArrayList<CarIssueDto>();

        List<CarIssue> carIssues;
        if (type == null) {
            carIssues = carIssueService.findCarIssuesByLicense(query);
        } else {
            carIssues = carIssueService.findCarIssuesByType(type);
        }

        for (CarIssue carIssue : carIssues) {
            dtos.add(CarIssueDto.fromCarIssue(carIssue));
        }

        return dtos;
    }

    @PostMapping
    public CarIssueDto saveCarIssue(@RequestBody CarIssueInputDto dto) {
        var carIssue = carIssueService.saveCarIssue(dto.toCarIssue(), dto.carId);
        System.out.println(carIssue.getIssueDescription());
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
