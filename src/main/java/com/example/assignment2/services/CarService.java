package com.example.assignment2.services;

import com.example.assignment2.entity.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional
public class CarService {
    @Autowired
    private SessionFactory sessionFactory;
    public CarService(){}
    public CarService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void saveCar(Car car){
        sessionFactory.getCurrentSession().save(car);
    }
}
