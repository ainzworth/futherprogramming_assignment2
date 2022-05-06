package com.example.assignment2.configurations;

import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.repositories.CarRepository;
import com.example.assignment2.repositories.DriverRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@EnableTransactionManagement
public class AppConfig{

    @Bean
    CommandLineRunner commandLineRunner(CarRepository repository, DriverRepository driverRepository){
        return args -> {
            Car car1 = new Car("america","model1","red","convertible","10","licence plate",
                    true,"rate per kilo");
            Car car2 = new Car("vietnam","model2","blue","convertible","5","licence plate",
                    true,"rate per kilo");
            repository.saveAll(
                    List.of(car1,car2)
            );

            Driver driver1 = new Driver("123","0908827",10,null);
            driverRepository.saveAll(
                    List.of(driver1));



        };
    }

}