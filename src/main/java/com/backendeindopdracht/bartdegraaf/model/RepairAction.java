package com.backendeindopdracht.bartdegraaf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class RepairAction {

    @Id
    @GeneratedValue
    Long id;

    String description;

    Double cost;


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getCost() {
        return cost;
    }

    @ManyToOne
    RepairEvent repairEvent;

    public RepairEvent getRepairEvent() {
        return repairEvent;
    }

    public void setRepairEvent(RepairEvent repairEvent) {
        this.repairEvent = repairEvent;
    }
}
