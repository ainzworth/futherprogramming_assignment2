package com.example.assignment2.controller;

import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.assignment2.services.CarService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }
    // read
        // get car by make
    @GetMapping(path = "/make/{make}")
    public Optional<Car> getCarByMake(
            @PathVariable("make") String make
    ){ return carService.getCarByMake(make);}
        // get all cars
    @GetMapping
    public List<Car> getCars(){
        return carService.getCars();
    }
    // Create
    @PostMapping
    public void addCar(@RequestBody Car car){
        carService.addNewCar(car);
    }
    // Update
    @PutMapping(path = "{VIN}")
    public void updateCarById(
            @PathVariable("VIN") Long VIN,
            @RequestParam(required = false) Boolean availability,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String convertible,
            @RequestParam(required = false) String licence_plate,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String rate_per_kilo,
            @RequestParam(required = false) String rating
    ){
        carService.updateCar(VIN,availability,color,convertible,licence_plate,make,
        model,rate_per_kilo,rating);
    }

    @DeleteMapping(path = "{VIN}")
    public void deleteCar(@PathVariable("VIN") Long VIN)
    {
        carService.deleteCar(VIN);
    }







}
