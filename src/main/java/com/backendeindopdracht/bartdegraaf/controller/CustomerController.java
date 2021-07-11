package com.backendeindopdracht.bartdegraaf.controller;

import com.backendeindopdracht.bartdegraaf.controller.dto.CustomerDto;
import com.backendeindopdracht.bartdegraaf.controller.dto.CustomerInputDto;
import com.backendeindopdracht.bartdegraaf.exceptions.BadRequestException;
import com.backendeindopdracht.bartdegraaf.exceptions.DefaultExceptionWithMessage;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
