package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.lowagie.text.Document;

import java.io.FileNotFoundException;
import java.util.List;

public interface RepairEventService {
    RepairEvent saveRepairEvent(RepairEvent toRepairEvent, Long customerId);

    RepairEvent updateRepairEvent(RepairEvent repairEvent, Long customerId);

    void deleteRepairEvent(Long id);

    List<RepairEvent> getRepairEvents();

    RepairEvent getRepairEvent(Long id);

    String getRepairEventInvoice(Long id) throws FileNotFoundException;
}
