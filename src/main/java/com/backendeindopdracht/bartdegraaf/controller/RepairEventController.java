package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.RepairEventDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.RepairEventInputDto;
import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.backendeindopdracht.bartdegraaf.service.RepairEventService;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @GetMapping("/{id}/invoice")
    public ResponseEntity<byte[]> getPapers(@PathVariable("id") Long id) throws IOException {
        var pdfPath = repairEventService.getRepairEventInvoice(id);

        byte[] bytes = Files.readAllBytes(Paths.get(pdfPath));

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"invoice.pdf\"")
                .body(bytes);
    }
}
