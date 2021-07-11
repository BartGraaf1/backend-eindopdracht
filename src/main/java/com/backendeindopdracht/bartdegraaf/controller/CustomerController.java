package com.novi.easyboat.controllers;

import com.novi.easyboat.controllers.dto.CustomerDto;
import com.novi.easyboat.controllers.dto.CustomerInputDto;
import com.novi.easyboat.model.Customer;
import com.novi.easyboat.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDto> getCustomers() {
        var dtos = new ArrayList<CustomerDto>();
        var customers = customerService.getCustomers();

        for (Customer customer : customers) {
            dtos.add(CustomerDto.fromCustomer(customer));
        }

        return dtos;
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable("id") Long id) {
        var customer = customerService.getCustomer(id);
        return CustomerDto.fromCustomer(customer);
    }

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody CustomerInputDto dto) {
        var customer = customerService.saveCustomer(dto.toCustomer());
        return CustomerDto.fromCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}
