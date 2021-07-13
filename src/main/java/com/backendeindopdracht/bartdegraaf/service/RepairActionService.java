package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.RepairAction;

import java.util.List;

public interface RepairActionService {
    List<RepairAction> getRepairActionsForCustomer(Long customerId);

    List<RepairAction> findRepairActionsByLicense(String query);

    List<RepairAction> findRepairActionsByType(String type);

    RepairAction saveRepairAction(RepairAction toRepairAction, Long customerId);

    RepairAction updateRepairAction(RepairAction repairAction, Long customerId);

    void deleteRepairAction(Long id);

    List<RepairAction> getRepairActions();

    RepairAction getRepairAction(Long id);
}
