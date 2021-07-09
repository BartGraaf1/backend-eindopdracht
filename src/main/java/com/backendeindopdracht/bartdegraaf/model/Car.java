package com.backendeindopdracht.bartdegraaf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Car car;

    String name;

    String type;

    // Getters
    public Long getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBoat(Car car) {
        this.car = car;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
