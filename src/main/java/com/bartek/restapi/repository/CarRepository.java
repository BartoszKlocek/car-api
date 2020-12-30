package com.bartek.restapi.repository;

import com.bartek.restapi.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    List<Car> findAll();

    Optional<Car> findById(long id);

    List<Car> findByColor(String color);

}
