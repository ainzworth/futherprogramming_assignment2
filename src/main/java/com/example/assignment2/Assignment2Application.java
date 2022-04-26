package com.example.assignment2;

import com.example.assignment2.appConfig.AppConfig;
import com.example.assignment2.services.CarService;
import com.example.assignment2.services.DriverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Assignment2Application {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		CarService carService1 = context.getBean(CarService.class);
		DriverService driverService1 = context.getBean(DriverService.class);



	}

}
