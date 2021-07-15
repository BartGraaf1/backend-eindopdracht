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

    Boolean isEventApproved;


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRoutineService(Boolean routineService) {
        isRoutineService = routineService;
    }

    public void setDateOfEvent(LocalDateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public void setIsEventPayed(Boolean evenIsPayed) {
        this.isEventPayed = evenIsPayed;
    }

    public void setIsEventApproved(Boolean isEventApproved) { this.isEventApproved = isEventApproved;  }

    // Getters
    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Boolean getRoutineService() {
        return isRoutineService;
    }

    public LocalDateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public Boolean getIsEventPayed() {
        return isEventPayed;
    }

    public Boolean getIsEventApproved() { return isEventApproved; }


    @ManyToOne
    Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
