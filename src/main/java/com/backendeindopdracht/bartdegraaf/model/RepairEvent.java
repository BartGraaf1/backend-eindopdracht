package com.backendeindopdracht.bartdegraaf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class RepairEvent {

    @Id
    @GeneratedValue
    Long id;

    String comment;

    LocalDateTime dateOfEvent;

    Boolean isRoutineService;

    Boolean isEventPayed;

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(LocalDateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Boolean getRoutineService() {
        return isRoutineService;
    }

    public void setRoutineService(Boolean routineService) {
        isRoutineService = routineService;
    }

    public Boolean getIsEventPayed() {
        return isEventPayed;
    }

    public void setIsEventPayed(Boolean evenIsPayed) {
        this.isEventPayed = evenIsPayed;
    }

    @ManyToOne
    Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
