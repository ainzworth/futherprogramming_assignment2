package com.example.assignment2.controller;


import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.services.CarService;
import com.example.assignment2.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    // read
    @GetMapping
    public List<Driver> getDrivers(){
        return driverService.getDrivers();
    }
    // create
    @PostMapping
    public void addDriver(@RequestBody Driver driver){
        driverService.addDriver(driver);
    }
    // update
    @PutMapping(path = "updateDriver/{driver_id}")
    public void updateDriver(
            @PathVariable("driver_id") Long driverId,
            @RequestParam(required = false) String licenceNum,
            @RequestParam(required = false) String phoneNum,
            @RequestParam(required = false) Double rating
    ){
        driverService.updateDriver(driverId,licenceNum,phoneNum,rating);
    }
        // update car for driver
        @PutMapping(path ="{licenceNum}")
        public void addCar(
            @PathVariable("licenceNum") String licenceNum,
            @RequestParam(required = false) Long VIN){
            Optional<Car> carByVIN = carService.getCarRepository().findById(VIN);
            // check if id exist
            if (carByVIN.isEmpty()) {
                throw new IllegalStateException("this car id is not exist");
            }
            // if car is selected throw error
            if(!carByVIN.get().getAvailability()) {
                throw new IllegalStateException("this car is taken");
            }

            // update availability
            driverService.addCar(licenceNum, carByVIN);

        }
    // delete
    @DeleteMapping(path = "{licenceNum}")
    public void deleteDriver(
            @PathVariable("licenceNum") String licenceNum
    ){
        driverService.deleteDriver(licenceNum);
    }

}
