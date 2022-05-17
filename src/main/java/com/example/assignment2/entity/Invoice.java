package com.example.assignment2.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table
public class Invoice{

    @Id
    @SequenceGenerator(
            name = "invoice_sequence",
            sequenceName = "invoice_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_sequence"
    )
    private Long invoiceId;


    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            insertable = false,
            updatable = false
    )
    private Customer customer;

    @ManyToOne
    @JoinColumn(
            name = "driver_id",
            insertable = false,
            updatable = false
    )
    private Driver driver;
    private double totalChange;

    @OneToOne(mappedBy = "invoice")
    private Booking booking;
    public Invoice(double totalChange){
        this.totalChange = totalChange;
    };
    public Invoice(Driver driver,Customer customer,double totalChange) {
        this.driver = driver;
        this.customer = customer;
        this.totalChange = totalChange;
    }
    @Transient
    private Long customerId;
    @Transient
    private Long driverId;

    public Invoice() {

    }

    public Long getId() {
        return invoiceId;
    }

    public void setId(Long id) {
        this.invoiceId = id;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
