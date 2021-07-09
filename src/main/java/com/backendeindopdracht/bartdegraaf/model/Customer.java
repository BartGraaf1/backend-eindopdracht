package com.backendeindopdracht.bartdegraaf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

public class Customer {
    @Id
    @GeneratedValue
    Long id;

    String firstname;

    String lastname;

    String phoneNumber;

    @Lob
    byte[] license;

    @OneToMany(mappedBy = "car")
    List<Car> car;

    public Long getId() {
        return id;
    }

    // Getters
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
