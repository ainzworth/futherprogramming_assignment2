package com.example.assignment2.services;


import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }
    public List<Driver> getDrivers(){
        return driverRepository.findAll();
    }

    // allocate car

    public void addCar(String licence,Optional<Car> car)
    {
        Optional<Driver> driverByLicence = driverRepository.findDriverByLicenceNum(licence);
        if(driverByLicence.isEmpty()){
            throw new IllegalStateException("driver does not exist");
        }
        driverRepository.addCarByDriver(driverByLicence,car);
    }


}
