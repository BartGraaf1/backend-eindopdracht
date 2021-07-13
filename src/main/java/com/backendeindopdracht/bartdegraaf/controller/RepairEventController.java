package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.RepairEventDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.RepairEventInputDto;
import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.backendeindopdracht.bartdegraaf.service.RepairEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("repairEvents")
public class RepairEventController {
    private final RepairEventService repairEventService;

    @Autowired
    public RepairEventController(RepairEventService repairEventService) {
        this.repairEventService = repairEventService;
    }

    @GetMapping
    public List<RepairEventDto> getCarissue() {
        var dtos = new ArrayList<RepairEventDto>();
        var repairEvents = repairEventService.getRepairEvents();

        for (RepairEvent repairEvent : repairEvents) {
            dtos.add(RepairEventDto.fromRepairEvent(repairEvent));
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public RepairEventDto getCustomer(@PathVariable("id") Long id) {
        var repairEvents= repairEventService.getRepairEvent(id);
        return RepairEventDto.fromRepairEvent(repairEvents);
    }

    @PostMapping
    public RepairEventDto saveRepairEvent(@RequestBody RepairEventInputDto dto) {
        var repairEvent = repairEventService.saveRepairEvent(dto.toRepairEvent(), dto.carId);
        return RepairEventDto.fromRepairEvent(repairEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteRepairEvent(@PathVariable("id") Long id) {
        repairEventService.deleteRepairEvent(id);
    }


    @PutMapping("")
    public RepairEventDto updateRepairEvent(@RequestBody RepairEventInputDto dto) {
        var repairEvent = repairEventService.updateRepairEvent(dto.toRepairEvent(), dto.carId);
        return RepairEventDto.fromRepairEvent(repairEvent);
    }
}
