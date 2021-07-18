package com.backendeindopdracht.bartdegraaf.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue
    Long id;

    String licensePlate;

    String type;

    @Lob
    byte[] papers;

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

    public byte[] getPapers() {return papers;}

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

    public void setPapers(byte[] papers) {
        this.papers = papers;
    }
}
