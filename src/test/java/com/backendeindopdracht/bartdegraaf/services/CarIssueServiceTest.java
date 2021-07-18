package com.backendeindopdracht.bartdegraaf.services;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.model.CarIssue;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.repository.CarIssueRepository;
import com.backendeindopdracht.bartdegraaf.service.CarIssueServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarIssueServiceTest {

    @Mock
    CarIssueRepository carIssueRepository;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    private CarIssueServiceImpl carIssueService;
    
    @Captor
    ArgumentCaptor<CarIssue> CarIssueCaptor;
    
    @Test
    public void saveCarIssueTest() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setEmailAddress("Bart.graaf06@gmail.com");
        customer.setFirstname("Bart");
        customer.setLastname("de Graaf");
        customer.setPhoneNumber("0639783289");


        Car car = new Car();
        car.setId(1L);
        car.setCustomer(customer);
        car.setType("car");
        car.setLicensePlate("8-xvt-33");

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        CarIssue carIssue = new CarIssue();
        carIssue.setId(1L);
        carIssue.setIssueDescription("Just a regular issue");

        carIssueService.saveCarIssue(carIssue, car.getId());

        verify(carIssueRepository).save(CarIssueCaptor.capture());

        var currEvent = CarIssueCaptor.getValue();
        assertThat(currEvent.getIssueDescription().equals("Just a regular issue"));
        assertThat(currEvent.getCar().equals(car));
    }

}
