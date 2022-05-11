package com.example.assignment2.entity;



import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Driver {
    @Id
    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "driver_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_sequence"
    )
    private Long driverId;
    private String licenceNum;
    private String phoneNumber;
    private double rating;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Invoice> invoices = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Car_id", referencedColumnName = "VIN")
    private Car car;

    public Driver(){}
    public Driver( String licenceNum, String phoneNumber, double rating, Car car) {
        this.licenceNum = licenceNum;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.car = car;
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }



    public Long getId() {
        return driverId;
    }

    public void setId(Long id) {
        this.driverId = id;
    }

    public String getLicenceNum() {
        return licenceNum;
    }

    public void setLicenceNum(String licenceNum) {
        this.licenceNum = licenceNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    public Set<Invoice> getInvoices(){
        return this.invoices;
    }
    public void setInvoices(Set<Invoice> invoices){
        this.invoices = invoices;
    }
    @Transactional
    public void addInvoice(Invoice invoice){
        Set<Invoice> newInvoices = this.invoices;
        invoice.setDriver(this);
        newInvoices.add(invoice);
        setInvoices(newInvoices);
    }
}
