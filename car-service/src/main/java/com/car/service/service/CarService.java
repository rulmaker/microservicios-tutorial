package com.car.service.service;

import com.car.service.entity.Car;
import com.car.service.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> byUserId(int userId){
        return carRepository.findByUsuarioId(userId);
    }
}
