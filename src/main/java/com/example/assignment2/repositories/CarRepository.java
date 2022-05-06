package com.example.assignment2.repositories;

import com.example.assignment2.entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car,Long> {


    @Query("SELECT c FROM Car c where c.make = ?1")
    Optional<Car> findCarByMake(String make);
    @Query("SELECT c FROM Car c where c.licencePlate = ?1")
    Optional<Car> findCarByLicencePlate(String licencePlate);



}

