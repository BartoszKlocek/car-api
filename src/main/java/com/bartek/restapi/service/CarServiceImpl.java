package com.bartek.restapi.service;

import com.bartek.restapi.model.Car;

import com.bartek.restapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    CarRepository repository;

    @Autowired
    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Car> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Car> findCarById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Car> findCarByColor(String color) {
        return repository.findByColor(color);
    }

    @Override
    public boolean addCar(Car car) {
        return repository.save(car);
    }

    @Override
    public boolean editCar(long id, Car car) {
        return repository.update(id, car);
    }

    @Override
    public boolean removeCar(long id) {
        return repository.delete(id);
    }

}
