package com.example.assignment2;

import appConfig.AppConfig;
import services.CarService;
import services.DriverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Assignment2Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		CarService carService1 = context.getBean(CarService.class);
		DriverService driverService1 = context.getBean(DriverService.class);



	}

}
