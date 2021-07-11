package com.novi.easyboat.controllers.dto;

import com.novi.easyboat.model.Customer;

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
