package com.example.assignment2.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
//@EqualsAndHashCode(callSuper = true)
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )
    private Long VIN;
    private String make;

    private String model;

    private String color;

    private String convertible;

    private String rating;

    private String licencePlate;

    private Boolean availability;

    private String ratePerKilo;


    @OneToOne(mappedBy = "car")
    private Driver driver;



//    private CarService carService;




    public Car(String make, String model, String color, String convertible, String rating, String licencePlate,
               Boolean availability, String ratePerKilo) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.convertible = convertible;
        this.rating = rating;
        this.licencePlate = licencePlate;
        this.availability = availability;
        this.ratePerKilo = ratePerKilo;
    }


    public Car(){
    }



    public Long getVIN() {
        return VIN;
    }


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getConvertible() {
        return convertible;
    }

    public void setConvertible(String convertible) {
        this.convertible = convertible;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getRatePerKilo() {
        return ratePerKilo;
    }

    public void setRatePerKilo(String ratePerKilo) {
        this.ratePerKilo = ratePerKilo;
    }
}
