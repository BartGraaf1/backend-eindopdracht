package com.novi.easyboat.controllers.dto;

import com.novi.easyboat.model.Boat;

public class BoatInputDto {
    public String name;
    public String type;

    public Boat toBoat() {
        var boat = new Boat();
        boat.setName(name);
        boat.setType(type);
        return boat;
    }
}
