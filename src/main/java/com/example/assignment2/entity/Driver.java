package com.example.assignment2.entity;

import javax.persistence.*;

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

    private Long id;
    private String licenceNum;
    private String phoneNumber;
    private double rating;


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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
