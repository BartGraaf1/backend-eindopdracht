package com.backendeindopdracht.bartdegraaf.model;

import com.backendeindopdracht.bartdegraaf.controller.dto.CustomerDto;

import javax.persistence.*;
@Entity
public class CarIssue {

    @Id
    @GeneratedValue
    Long id;

    String issueDescription;

    @ManyToOne
    Car car;

    // Getters
    public Long getId() {
        return id;
    }

    public String getIssueDescription(){
        return issueDescription;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setIssueDescription(String issueDescription){
        this.issueDescription = issueDescription;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
