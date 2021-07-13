package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import com.backendeindopdracht.bartdegraaf.repository.RepairActionRepository;
import com.backendeindopdracht.bartdegraaf.repository.RepairEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairActionServiceImpl implements RepairActionService {
    private final RepairActionRepository repairActionRepository;
    private final RepairEventRepository repairEventRepository;

    @Autowired
    public RepairActionServiceImpl(RepairActionRepository repairActionRepository, RepairEventRepository repairEventRepository) {
        this.repairActionRepository = repairActionRepository;
        this.repairEventRepository = repairEventRepository;
    }

    @Override
    public List<RepairAction> getRepairActionsForCustomer(Long customerId) {
        return null;
    }

    @Override
    public List<RepairAction> findRepairActionsByLicense(String query) {
        return null;
    }

    @Override
    public List<RepairAction> findRepairActionsByType(String type) {
        return null;
    }

    @Override
    public RepairAction saveRepairAction(RepairAction toRepairAction, Long repairEventId) {
        var optionalRepairEvent = repairEventRepository.findById(repairEventId);

        if (optionalRepairEvent.isEmpty()) {
            throw new NotFoundException();
        }

        var repairEvent = optionalRepairEvent.get();
        var repairAction = new RepairAction();
        repairAction.setId(toRepairAction.getId());
        repairAction.setRepairEvent(repairEvent);
        repairAction.setDescription(toRepairAction.getDescription());
        repairAction.setCost(toRepairAction.getCost());

        return repairActionRepository.save(repairAction);
    }


    @Override
    public RepairAction updateRepairAction(RepairAction toRepairAction, Long repairEventId) {
        var optionalRepairEvent = repairEventRepository.findById(repairEventId);

        if (optionalRepairEvent.isEmpty()) {
            throw new NotFoundException();
        }

        var repairEvent = optionalRepairEvent.get();
        var repairAction = new RepairAction();
        repairAction.setId(toRepairAction.getId());
        repairAction.setRepairEvent(repairEvent);
        repairAction.setDescription(toRepairAction.getDescription());
        repairAction.setCost(toRepairAction.getCost());

        Optional<RepairAction> optionalRepairActionToUpdate = repairActionRepository.findById(repairAction.getId());
        if(optionalRepairActionToUpdate.isPresent()) {
            return repairActionRepository.save(repairAction);
        } else {
            throw new RecordNotFoundException("RepairEvent issue does not exist");
        }
    }

    @Override
    public void deleteRepairAction(Long id) {
        repairActionRepository.deleteById(id);
    }

    @Override
    public List<RepairAction> getRepairActions() {
        return repairActionRepository.findAll();
    }

    @Override
    public RepairAction getRepairAction(Long id) {
        Optional<RepairAction> optionalRepairAction = repairActionRepository.findById(id);
        if(optionalRepairAction.isPresent()) {
            return repairActionRepository.getById(id);
        }else{
            throw new RecordNotFoundException("RepairEvent issue does not exist");
        }
    }
}
