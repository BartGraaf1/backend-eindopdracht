package com.backendeindopdracht.bartdegraaf.services;

import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.repository.CustomerRepository;
import com.backendeindopdracht.bartdegraaf.repository.CustomerRepository;
import com.backendeindopdracht.bartdegraaf.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;
    
    @InjectMocks
    private CustomerServiceImpl customerService;
    
    @Captor
    ArgumentCaptor<Customer> CustomerCaptor;
    
    @Test
    public void saveCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setEmailAddress("Bart.graaf06@gmail.com");
        customer.setFirstname("Bart");
        customer.setLastname("de Graaf");
        customer.setPhoneNumber("0639783289");

        customerService.saveCustomer(customer);

        verify(customerRepository).save(CustomerCaptor.capture());

        var currCustomer = CustomerCaptor.getValue();
        assertThat(currCustomer.getEmailAddress().equals("Bart.graaf06@gmail.com"));
        assertThat(currCustomer.getFirstname().equals("Bart"));
        assertThat(currCustomer.getLastname().equals("de Graaf"));
        assertThat(currCustomer.getPhoneNumber().equals("0639783289"));
    }

}
