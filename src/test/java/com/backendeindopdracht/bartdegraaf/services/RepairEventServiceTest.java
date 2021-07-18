package com.backendeindopdracht.bartdegraaf.services;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.backendeindopdracht.bartdegraaf.repository.RepairEventRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RepairEventServiceTest {

    @Mock
    RepairEventRepository repairEventRepository;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    private RepairEventServiceImpl repairEventService;
    
    @Captor
    ArgumentCaptor<RepairEvent> RepairEventCaptor;
    
    @Test
    public void saveRepairEventTest() {
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

        RepairEvent repairEvent = new RepairEvent();
        repairEvent.setId(1L);
        repairEvent.setComment("Just a regular event");
        repairEvent.setDateOfEvent(LocalDateTime.parse("2021-01-01T16:00:00"));
        repairEvent.setIsEventPayed(true);
        repairEvent.setIsEventApproved(true);
        repairEvent.setRoutineService(true);

        repairEventService.saveRepairEvent(repairEvent, car.getId());

        verify(repairEventRepository).save(RepairEventCaptor.capture());

        var currEvent = RepairEventCaptor.getValue();
        assertThat(currEvent.getComment().equals("Just a regular event"));
        assertThat(currEvent.getDateOfEvent().equals(LocalDateTime.parse("2021-01-01T16:00:00")));
        assertThat(currEvent.getIsEventPayed().equals(true));
        assertThat(currEvent.getIsEventApproved().equals(true));
        assertThat(currEvent.getRoutineService().equals(true));
        assertThat(currEvent.getCar().equals(car));
    }

}
