package com.novi.easyboat.controllers.dto;

import com.novi.easyboat.model.Customer;

public class CustomerInputDto {
    public String firstname;
    public String lastname;
    public String phoneNumber;
    public String emailAddress;

    public Customer toCustomer() {
        var customer = new Customer();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmailAddress(emailAddress);
        return customer;
    }
}
