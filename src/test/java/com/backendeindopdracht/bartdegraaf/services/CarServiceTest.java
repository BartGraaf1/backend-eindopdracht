package com.backendeindopdracht.bartdegraaf.services;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.repository.CustomerRepository;
import com.backendeindopdracht.bartdegraaf.repository.RepairEventRepository;
import com.backendeindopdracht.bartdegraaf.service.CarServiceImpl;
import com.backendeindopdracht.bartdegraaf.service.RepairEventServiceImpl;
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
public class CarServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;
    
    @Captor
    ArgumentCaptor<Car> CarCaptor;
    
    @Test
    public void saveCar() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setEmailAddress("Bart.graaf06@gmail.com");
        customer.setFirstname("Bart");
        customer.setLastname("de Graaf");
        customer.setPhoneNumber("0639783289");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));


        Car car = new Car();
        car.setId(1L);
        car.setCustomer(customer);
        car.setType("car");
        car.setLicensePlate("8-xvt-33");

        carService.saveCar(car, customer.getId());

        verify(carRepository).save(CarCaptor.capture());

        var currCar = CarCaptor.getValue();
        assertThat(currCar.getType().equals("car"));
        assertThat(currCar.getLicensePlate().equals("8-xvt-33"));
        assertThat(currCar.getCustomer().equals(customer));
    }

}
