package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer getCustomer(Long id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer toCustomer);

    void deleteCustomer(Long id);

    List<Customer> getCustomerForCar(Long carId);

    List<Customer> getCarForCustomer(Long customerId);

}
