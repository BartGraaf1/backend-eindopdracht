package com.backendeindopdracht.bartdegraaf.services;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.backendeindopdracht.bartdegraaf.repository.RepairActionRepository;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
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
public class RepairActionServiceTest {

    @Mock
    RepairActionRepository repairActionRepository;

    @Mock
    RepairEventRepository repairEventRepository;

    @InjectMocks
    private RepairActionServiceImpl repairActionService;
    
    @Captor
    ArgumentCaptor<RepairAction> RepairActionCaptor;
    
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

        when(repairEventRepository.findById(1L)).thenReturn(Optional.of(repairEvent));

        RepairAction repairAction = new RepairAction();
        repairAction.setId(1L);
        repairAction.setDescription("Just a regular action");
        repairAction.setCost(10.00);
        repairAction.setRepairEvent(repairEvent);

        repairActionService.saveRepairAction(repairAction, car.getId());

        verify(repairActionRepository).save(RepairActionCaptor.capture());

        var currEvent = RepairActionCaptor.getValue();
        assertThat(currEvent.getDescription().equals("Just a regular action"));
        assertThat(currEvent.getCost().equals(10.00));
        assertThat(currEvent.getRepairEvent().equals(repairEvent));
    }

}
