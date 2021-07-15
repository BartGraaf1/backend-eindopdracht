package com.backendeindopdracht.bartdegraaf.controller.dto;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CustomerDto {
    public Long id;
    public String firstname;
    public String lastname;
    public String phoneNumber;
    public String emailAddress;

    public static CustomerDto fromCustomer(Customer customer) {
        var dto = new CustomerDto();
        dto.id = customer.getId();
        dto.firstname = customer.getFirstname();
        dto.lastname = customer.getLastname();
        dto.phoneNumber = customer.getPhoneNumber();
        dto.emailAddress = customer.getEmailAddress();
        return dto;
    }
}
