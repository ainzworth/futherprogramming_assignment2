package entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "VIN")
    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "car_sequence"

    )

    private String VIN;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private String color;
    @Column
    private String convertible;
    @Column
    private String rating;
    @Column
    private String licencePlate;
    @Column
    private Boolean availability;
    @Column
    private String ratePerKilo;

    @OneToOne(mappedBy = "car")
    private Driver driver;

    public Car(String VIN, String make, String model, String color, String convertible, String rating, String licencePlate,
               Boolean availability, String ratePerKilo, Driver driver) {
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.color = color;
        this.convertible = convertible;
        this.rating = rating;
        this.licencePlate = licencePlate;
        this.availability = availability;
        this.ratePerKilo = ratePerKilo;
        this.driver = driver;
    }



    public Car(){
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
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
