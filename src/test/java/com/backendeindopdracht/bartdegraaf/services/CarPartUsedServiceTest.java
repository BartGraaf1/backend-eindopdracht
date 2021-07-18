package com.backendeindopdracht.bartdegraaf.services;

import com.backendeindopdracht.bartdegraaf.model.*;
import com.backendeindopdracht.bartdegraaf.repository.CarPartUsedRepository;
import com.backendeindopdracht.bartdegraaf.repository.RepairActionRepository;
import com.backendeindopdracht.bartdegraaf.repository.RepairEventRepository;
import com.backendeindopdracht.bartdegraaf.service.RepairActionServiceImpl;
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
public class CarPartUsedServiceTest {

    @Mock
    CarPartUsedRepository carPartUsedRepository;
    
    @Captor
    ArgumentCaptor<CarPartUsed> CarPartUsedCaptor;
    
    @Test
    public void saveRepairActionTest() {
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

        RepairEvent repairEvent = new RepairEvent();
        repairEvent.setId(1L);
        repairEvent.setComment("Just a regular event");
        repairEvent.setDateOfEvent(LocalDateTime.parse("2021-01-01T16:00:00"));
        repairEvent.setIsEventPayed(true);
        repairEvent.setIsEventApproved(true);
        repairEvent.setRoutineService(true);

        RepairAction repairAction = new RepairAction();
        repairAction.setId(1L);
        repairAction.setDescription("Just a regular action");
        repairAction.setCost(10.00);
        repairAction.setRepairEvent(repairEvent);

        CarPartStock carPartStock = new CarPartStock();
        carPartStock.setId(1L);
        carPartStock.setStock(50L);
        carPartStock.setDescription("This is a general description");
        carPartStock.setPrice(10.00);

        CarPartUsed carPartUsed = new CarPartUsed();
        carPartUsed.setAmountUsed(1L);
        carPartUsed.setRepairAction(repairAction);
        carPartUsed.setCarPartStock(carPartStock);

        carPartUsedRepository.save(carPartUsed);

        verify(carPartUsedRepository).save(CarPartUsedCaptor.capture());

        var currCarPartUsed = CarPartUsedCaptor.getValue();
        assertThat(currCarPartUsed.getAmountUsed().equals(1L));
        assertThat(currCarPartUsed.getRepairEvent().equals(repairAction));
        assertThat(currCarPartUsed.getCarPartStock().equals(carPartStock));
    }

}
