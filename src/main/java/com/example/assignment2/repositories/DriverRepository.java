package com.example.assignment2.repositories;

import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query("SELECT d FROM Driver d where d.licenceNum = ?1")
    Optional<Driver> findDriverByLicenceNum(String licenceNum);

    @Modifying
    @Transactional
    @Query("update Driver d set d.car = ?2 where d = ?1")
    void addCarByDriver(Optional<Driver> driver, Optional<Car> car);

}
