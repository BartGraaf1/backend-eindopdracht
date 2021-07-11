package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) {
        return repository.getById(id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomerForCar(Long carId) {
        return null;
    }

    @Override
    public List<Customer> getCarForCustomer(Long customerId) {
        return null;
    }
}
