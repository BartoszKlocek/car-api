package com.bartek.restapi.controller;

import com.bartek.restapi.model.Car;
import com.bartek.restapi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService service;

    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Car>> allCars() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> carById(@PathVariable long id){
        Optional<Car> first = service.findCarById(id);
        return first
                .map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> allCarsByColor(@PathVariable String color){
        return new ResponseEntity<>(service.findCarByColor(color),HttpStatus.OK);
    }
}
