package com.bartek.restapi.service;

import com.bartek.restapi.model.Car;
import com.bartek.restapi.model.Color;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAll();
    Optional<Car> findCarById(long id);
    List<Car> findCarByColor(String color);
    boolean addCar(Car car);
    boolean editCar(long id, Car car);
    boolean removeCar(long id);
}
