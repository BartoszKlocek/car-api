package com.bartek.restapi.repository;

import com.bartek.restapi.model.Car;
import com.bartek.restapi.model.Color;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private final List<Car> cars;

    public CarRepositoryImpl() {
        cars = new ArrayList<>();
        Car car1 = new Car(1L, "Kia", "Sportage", Color.WHITE);
        Car car2 = new Car(2L, "Hyundai", "SantaFe", Color.WHITE);
        Car car3 = new Car(3L, "Ford", "Fiesta", Color.BLUE);
        Car car4 = new Car(4L, "BMW", "X5", Color.YELLOW);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
    }

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public Optional<Car> findById(long id) {
        return cars.stream().filter(a -> a.getId() == id).findFirst();
    }

    @Override
    public List<Car> findByColor(String color) {
        return this.cars.stream()
                .filter(a -> Objects.equals(a.getColor(), Color.valueOf(color.toUpperCase())))
                .collect(Collectors.toList());
    }
}
