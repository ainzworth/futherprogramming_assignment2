package com.example.assignment2.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.transaction.Transactional;
//@Entity
//@Table(name = "Invoice")
public class Invoice {

    private Long id;
    @Autowired
    private Customer customer;
    @Autowired
    private Driver driver;
    private double totalChange;

    private Booking booking;

    public Invoice(Long id, Customer customer, Driver driver, double totalChange) {
        this.id = id;
        this.customer = customer;
        this.driver = driver;
        this.totalChange = totalChange;
    }

    public Invoice() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(double totalChange) {
        this.totalChange = totalChange;
    }
}
