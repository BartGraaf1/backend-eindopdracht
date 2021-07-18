package com.backendeindopdracht.bartdegraaf.services;

import com.backendeindopdracht.bartdegraaf.model.CarPartStock;
import com.backendeindopdracht.bartdegraaf.repository.CarPartStockRepository;
import com.backendeindopdracht.bartdegraaf.service.CarPartStockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarPartStockServiceTest {

    @Mock
    CarPartStockRepository carPartStockRepository;
    
    @InjectMocks
    private CarPartStockServiceImpl carPartStockService;
    
    @Captor
    ArgumentCaptor<CarPartStock> CarPartStockCaptor;
    
    @Test
    public void saveCarPartStock() {
        CarPartStock carPartStock = new CarPartStock();
        carPartStock.setId(1L);
        carPartStock.setStock(50L);
        carPartStock.setDescription("This is a general description");
        carPartStock.setPrice(10.00);

        carPartStockService.saveCarPartStock(carPartStock);

        verify(carPartStockRepository).save(CarPartStockCaptor.capture());

        var currCarPartStock = CarPartStockCaptor.getValue();
        assertThat(currCarPartStock.getStock().equals(50L));
        assertThat(currCarPartStock.getDescription().equals("This is a general description"));
        assertThat(currCarPartStock.getPrice().equals(10.00));
    }

}
