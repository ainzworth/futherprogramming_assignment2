package entity;

import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

//@Entity
//@Table(name = "booking")
public class Booking {
//    @Id
//    @Column(name = "booking_id")
//    @SequenceGenerator(
//            name = "booking_sequence",
//            sequenceName = "booking_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = SEQUENCE,
//            generator = "booking_sequence"
//
//    )
    private Long id;

    private String startingLocation;

    private String endLocation;

    private Date pickDate;

    private Date dropDate;

    private Double distance;


    private Invoice invoice;


    public Booking(Long id,
                   String startingLocation,
                   String endLocation,
                   Date pickDate,
                   Date dropDate,
                   Double distance,
                   Invoice invoice) {
        this.id = id;
        this.startingLocation = startingLocation;
        this.endLocation = endLocation;
        this.pickDate = pickDate;
        this.dropDate = dropDate;
        this.distance = distance;
        this.invoice = invoice;
    }

    public Booking() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public Date getPickDate() {
        return pickDate;
    }

    public void setPickDate(Date pickDate) {
        this.pickDate = pickDate;
    }

    public Date getDropDate() {
        return dropDate;
    }

    public void setDropDate(Date dropDate) {
        this.dropDate = dropDate;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
