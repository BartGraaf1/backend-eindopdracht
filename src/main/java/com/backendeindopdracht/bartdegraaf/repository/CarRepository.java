package com.backendeindopdracht.bartdegraaf.repository;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByPlannedStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Car> findByCustomer(Customer customer);

    List<Car> findByCar(Car car);
}
