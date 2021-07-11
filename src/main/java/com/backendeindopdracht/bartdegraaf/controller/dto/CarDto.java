package com.novi.easyboat.controllers.dto;

import com.novi.easyboat.model.Boat;

public class BoatDto {
    public Long id;
    public String name;
    public String type;

    public static BoatDto fromBoat(Boat boat) {
        if (boat == null) return null;

        var dto = new BoatDto();
        dto.id = boat.getId();
        dto.name = boat.getName();
        dto.type = boat.getType();
        return dto;
    }
}
