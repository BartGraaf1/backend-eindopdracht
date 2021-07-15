package com.backendeindopdracht.bartdegraaf.model;

import javax.persistence.*;

@Entity
public class CarPartUsed {

    @Id
    @GeneratedValue
    Long id;

    Long amountUsed;


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAmountUsed(Long amountUsed) {
        this.amountUsed = amountUsed;
    }


    // Getters
    public Long getId() {
        return id;
    }

    public Long getAmountUsed() {
        return amountUsed;
    }

    @ManyToOne
    RepairAction repairAction;

    public RepairAction getRepairEvent() {
        return repairAction;
    }

    public void setRepairEvent(RepairAction repairAction) {
        this.repairAction = repairAction;
    }

    @OneToOne
    CarPartStock carPartStock;

    public CarPartStock getCarPartStock() {
        return carPartStock;
    }

    public void setPartUsed(CarPartStock carPartStock) {
        this.carPartStock = carPartStock;
    }
}
