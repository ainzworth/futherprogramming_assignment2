package com.example.assignment2.services;

import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional
public class DriverService {
    @Autowired
    private SessionFactory sessionFactory;
    public DriverService(){};
    public DriverService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void saveDriver(Driver driver){
        sessionFactory.getCurrentSession().save(driver);
    }
}