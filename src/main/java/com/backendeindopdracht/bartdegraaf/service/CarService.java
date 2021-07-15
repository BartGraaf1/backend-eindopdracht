package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.model.Car;
import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;
import com.backendeindopdracht.bartdegraaf.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface CarService {
    List<Car> getCarsForCustomer(Long customerId);

    List<Car> findCarsByLicense(String query);

    List<Car> findCarsByType(String type);

    List<Car> getCars();

    Car saveCar(Car toCar, Long customerId);

    Car updateCar(Car car, Long customerId);

    Car getCar(Long id);

    void deleteCar(Long id);

    void uploadPapers(Long id, MultipartFile file) throws IOException;

    byte[] getPapers(Long id);


}
