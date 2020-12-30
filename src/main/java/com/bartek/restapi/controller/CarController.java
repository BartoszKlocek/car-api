package com.bartek.restapi.controller;

import com.bartek.restapi.model.Car;
import com.bartek.restapi.model.Color;
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
    public ResponseEntity<Car> carById(@PathVariable long id) {
        Optional<Car> first = service.findCarById(id);
        return first
                .map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> allCarsByColor(@PathVariable String color) {
        return new ResponseEntity<>(service.findCarByColor(color), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        if (service.addCar(car)) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCar(@PathVariable long id, @RequestBody Car car) {
        if (service.editCar(id, car)) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editAnyCarPosition(@PathVariable long id,
                                                @RequestParam(required = false) String model,
                                                @RequestParam(required = false) String mark,
                                                @RequestParam(required = false) String color) {
        if (service.findCarById(id).isPresent()) {
            Car car = service.findCarById(id).get();
            if (mark != null) {
                car.setMark(mark);
            }
            if (model != null) {
                car.setModel(model);
            }
            if (color != null) {
                car.setColor(Color.valueOf(color.toUpperCase()));
            }
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCar(@PathVariable long id) {
        if (service.findCarById(id).isPresent()) {
            Car car = service.findCarById(id).get();
            service.removeCar(id);
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
