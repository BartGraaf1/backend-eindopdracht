package com.backendeindopdracht.bartdegraaf.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class LicenseCheckerUtil {

    private final String pattern = "(([a-zA-Z]{3}[0-9]{3})|(\\w{2}-\\w{2}-\\w{2})|([0-9]{2}-[a-zA-Z]{3}-[0-9]{1})|([0-9]{1}-[a-zA-Z]{3}-[0-9]{2})|([a-zA-Z]{1}-[0-9]{3}-[a-zA-Z]{2}))$";

    private String licensePlate;
    

    public boolean isValidLicense() {
        return Pattern.matches(pattern, this.getLicensePlate());
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
