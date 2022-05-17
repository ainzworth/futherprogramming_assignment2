package com.example.assignment2.configurations;

import com.example.assignment2.entity.*;
import com.example.assignment2.repositories.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Configuration
@EnableTransactionManagement
public class AppConfig{

    @Bean
    CommandLineRunner commandLineRunner(CarRepository repository,
                                        DriverRepository driverRepository,
                                        BookingRepository bookingRepository,
                                        CustomerRepository customerRepository,
                                        InvoiceRepository invoiceRepository){
        return args -> {
            Car car1 = new Car("america","model1","red","convertible","10","licence plate",
                    true,"rate per kilo");
            Car car2 = new Car("vietnam","model2","blue","convertible","5","licence plate",
                    true,"rate per kilo");
            repository.saveAll(
                    List.of(car1,car2)
            );

            Driver driver1 = new Driver("123","0908827",10,null);
            Driver driver2 = new Driver("124","0901827",9,null);

            driverRepository.saveAll(
                    List.of(driver1,driver2));

            Booking booking1 = new Booking("hcm","ha noi","2017-02-02 08:59","2017-02-02 10:59", 100.0);

            bookingRepository.saveAll(
                    List.of(booking1)
            );
            Customer customer1 = new Customer("Green street 12","012012","customer1");
            Customer customer2 = new Customer("dark street 12","090009","customer1");
            Customer customer3 = new Customer("Green street 12","012012","customer3");

            customerRepository.saveAll(
                    List.of(customer1,customer2,customer3)
            );



        };
    }

}