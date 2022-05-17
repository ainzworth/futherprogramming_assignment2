package com.example.assignment2.services;

import com.example.assignment2.entity.Car;
import com.example.assignment2.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.transaction.Transactional;
//
//@Transactional
@Service
public class CarService {
    public final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }
    // read
    public List<Car> getCars(){
        return carRepository.findAll();
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }
    // read by make
//    public Optional<Car> getCarByMake(String make){
//        Optional<Car> carByMake = carRepository.findCarByMake(make);
//        if(!carByMake.isPresent()){
//            throw new IllegalStateException("cant find car with this make");
//        }else{
//            return carByMake;
//        }
//    }
    // update (check if make exist, if not add car to list)
    public void addNewCar(Car car){
        boolean carByMake = carRepository.selectCarByLicencePlate(car.getLicencePlate());
        if(carByMake){
            throw new IllegalStateException("Licence plate already exist");
        }
        carRepository.save(car);
    }
    // update
    @Transactional
    public void updateCar(Long carId,
                          Boolean availability,
                          String color,
                          String convertible,
                          String licence_plate,
                          String make,
                          String model,
                          String rate_per_kilo,
                          String rating){
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalStateException("car with id "+ carId +" does not exist")
        );
        // set VIN    String VIN = null;
        ////        if(VIN != null &&
        ////                VIN.length() > 0 &&){
        ////
        ////        }
//
        // set availability
        if(availability != null &&
                !Objects.equals(car.getAvailability(),availability)){
            car.setAvailability(availability);
        }
        // set color
        if(color != null && !Objects.equals(car.getColor(),color) ){
            car.setColor(color);
        }
        // set convertible
        if(convertible != null &&
        !Objects.equals(car.getConvertible(),convertible)){
            car.setConvertible(convertible);
        }
        // set licence_plate
        if(licence_plate != null &&
                !Objects.equals(car.getConvertible(),licence_plate)){
            car.setConvertible(licence_plate);
        }
        // set make
        if(make != null &&
        !Objects.equals(car.getMake(),make)){
            car.setMake(make);
        }
        // set model
        if(model != null &&
        !Objects.equals(car.getModel(),model)){
            car.setModel(model);
        }
        // set rate_per_kilo
        if(rate_per_kilo != null &&
        !Objects.equals(car.getRatePerKilo(),rate_per_kilo)){
            car.setRatePerKilo(rate_per_kilo);
        }
        // set rating
        if(rating != null &&
        !Objects.equals(car.getRating(),rating)){
            car.setRating(rating);
        }


    }
    // delete
    public void deleteCar(Long carId)
    {
        carRepository.findById(carId);
        boolean exist = carRepository.existsById((carId));
        if(!exist) {
            throw new IllegalStateException(
                    "car with id" + carId + "does not exist");
        }
        carRepository.deleteById(carId);
    }






}
