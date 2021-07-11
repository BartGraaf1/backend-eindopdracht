package com.backendeindopdracht.bartdegraaf.model;

import com.backendeindopdracht.bartdegraaf.controller.dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue
    Long id;

    String licensePlate;

    String type;

    @ManyToOne
    Customer customer;

    @OneToMany(mappedBy = "car")
    List<CarIssue> carIssues;

    // Getters
    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
