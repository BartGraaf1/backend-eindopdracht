package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = repository.findById(customer.getId());
        if(optionalCustomer.isPresent()) {
            return repository.save(customer);
        } else {
            throw new RecordNotFoundException("Customer does not exist");
        }
    }

    @Override
    public List<Customer> getCarForCustomer(Long customerId) {
        return null;
    }
}
