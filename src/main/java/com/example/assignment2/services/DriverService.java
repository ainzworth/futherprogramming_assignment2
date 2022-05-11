package com.example.assignment2.services;


import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }
    //read
    public List<Driver> getDrivers(){
        return driverRepository.findAll();
    }
    // create
    public void addDriver(Driver driver) {
        Optional<Driver> d1 = driverRepository.findDriverByLicenceNum(driver.getLicenceNum());
        if(d1.isPresent()){
            throw new IllegalStateException(
                    "driver with licence number " + driver.getLicenceNum()
                            + " already exist");
        }
        driverRepository.save(driver);
    }
    // update driver
    @Transactional
    public void updateDriver(Long driverId,
                             String licenceNum,
                             String phoneNum,
                             Double rating) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(
                ()->new IllegalStateException("driver with id " + driverId +" does not exist")
        );

        if(licenceNum != null &&
            !Objects.equals(driver.getLicenceNum(),licenceNum)){
            Optional<Driver> dr = driverRepository.findDriverByLicenceNum(licenceNum);
            if(dr.isPresent()){
                throw new IllegalStateException("driver with number licence " + licenceNum +" already exist");
            }
            driver.setLicenceNum(licenceNum);
        }
        if(phoneNum != null &&
                !Objects.equals(driver.getPhoneNumber(),phoneNum)){
            driver.setPhoneNumber(phoneNum);
        }
        if(rating != null &&
                !Objects.equals(driver.getRating(),rating)){
            driver.setRating(rating);
        }

    }
        // allocate car

        @Transactional
        public void addCar(String licence,Optional<Car> car)
        {
            // set car availability to false
            car.ifPresent(value -> value.setAvailability(false));
            Optional<Driver> driverByLicence = driverRepository.findDriverByLicenceNum(licence);
            if(driverByLicence.isEmpty()){
                throw new IllegalStateException("driver does not exist");
            }
            driverRepository.addCarByDriver(driverByLicence,car);
        }

    // delete
    public void deleteDriver(String licenceNum){
        Optional<Driver> driver = driverRepository.findDriverByLicenceNum(licenceNum);
        if(driver.isEmpty()){
            throw new IllegalStateException(
                    "driver with id" + licenceNum + "does not exist");
        }
        driverRepository.delete(driver.get());
    }



}
