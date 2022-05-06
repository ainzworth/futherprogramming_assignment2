package com.example.assignment2.controller;


import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.services.CarService;
import com.example.assignment2.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/driver")
public class DriverController {

    private final DriverService driverService;

    private final CarService carService;
    @Autowired
    public DriverController(DriverService driverService, CarService carService){
        this.driverService = driverService;
        this.carService = carService;
    }

    @GetMapping
    public List<Driver> getDrivers(){
        return driverService.getDrivers();
    }


    @PutMapping(path ="{licenceNum}")
    public void addCar(
        @PathVariable("licenceNum") String licenceNum,
        @RequestParam(required = false) Long VIN){
        Optional<Car> carByVIN = carService.getCarRepository().findById(VIN);
        // check if id exist
        if(carByVIN.isEmpty()){
            throw new IllegalStateException("this car id is not exist");
        }
        // update availability
        driverService.addCar(licenceNum, carByVIN);
    }

}
