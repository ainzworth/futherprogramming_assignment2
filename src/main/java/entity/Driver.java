package entity;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.sound.midi.Sequence;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "driver_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "driver_sequence"

    )
    private Long id;
    private String licenceNum;
    private String phoneNumber;
    private double rating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_VIN", referencedColumnName = "VIN")
    private Car car;

    public Driver(Long id, String licenceNum, String phoneNumber, double rating, Car car) {
        this.id = id;
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

    public Driver(){}


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
