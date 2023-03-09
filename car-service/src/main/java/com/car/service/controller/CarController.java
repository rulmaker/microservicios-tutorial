package com.car.service.controller;

import com.car.service.entity.Car;
import com.car.service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> listCars(){
        List<Car> cars = carService.getAll();

        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") int id){
        Car car = carService.getCarById(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        Car newCar = carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Car>> listCarsByUserId(@PathVariable("userId") int id){
        List<Car> cars = carService.byUserId(id);
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }


}
