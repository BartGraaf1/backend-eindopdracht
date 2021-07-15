package com.backendeindopdracht.bartdegraaf.model;

import javax.persistence.*;

@Entity
public class CarPartStock {

    @Id
    @GeneratedValue
    Long id;

    Double price;

    Long stock;

    String description;

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    // Getters
    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }


    public String getDescription() {
        return description;
    }
}
