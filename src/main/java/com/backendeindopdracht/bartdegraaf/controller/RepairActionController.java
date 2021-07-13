package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.RepairActionDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.RepairActionInputDto;
import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import com.backendeindopdracht.bartdegraaf.service.RepairActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("repairActions")
public class RepairActionController {
    private final RepairActionService repairActionService;

    @Autowired
    public RepairActionController(RepairActionService repairActionService) {
        this.repairActionService = repairActionService;
    }

    @GetMapping
    public List<RepairActionDto> getCarissue() {
        var dtos = new ArrayList<RepairActionDto>();
        var repairActions = repairActionService.getRepairActions();

        for (RepairAction repairAction : repairActions) {
            dtos.add(RepairActionDto.fromRepairAction(repairAction));
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public RepairActionDto getCustomer(@PathVariable("id") Long id) {
        var repairActions= repairActionService.getRepairAction(id);
        return RepairActionDto.fromRepairAction(repairActions);
    }

    @PostMapping
    public RepairActionDto saveRepairAction(@RequestBody RepairActionInputDto dto) {
        var repairAction = repairActionService.saveRepairAction(dto.toRepairAction(), dto.repairEventId);
        return RepairActionDto.fromRepairAction(repairAction);
    }

    @DeleteMapping("/{id}")
    public void deleteRepairAction(@PathVariable("id") Long id) {
        repairActionService.deleteRepairAction(id);
    }


    @PutMapping("")
    public RepairActionDto updateRepairAction(@RequestBody RepairActionInputDto dto) {
        var repairAction = repairActionService.updateRepairAction(dto.toRepairAction(), dto.repairEventId);
        return RepairActionDto.fromRepairAction(repairAction);
    }
}
