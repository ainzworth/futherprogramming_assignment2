package com.example.assignment2.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {
    @Id
    @SequenceGenerator(
            name = "booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booking_sequence"
    )
    private Long id;

    private String startingLocation;

    private String endLocation;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime pickDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dropDate;

    private Double distance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Invoice_id", referencedColumnName = "invoiceId")
    private Invoice invoice;
    @Transient
    private Long customerId;
    @Transient
    private Long driverId;
    public Booking(
                   String startingLocation,
                   String endLocation,
                   String pickDate,
                   String dropDate,
                   Double distance
                   ) throws Exception {
        this.startingLocation = startingLocation;
        this.endLocation = endLocation;
        try {
            this.pickDate = parseStringToLocalDate(pickDate);
            this.dropDate = parseStringToLocalDate(dropDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.distance = distance;
//        this.invoice = invoice;
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

    public LocalDateTime getPickDate() {
        return pickDate;
    }

    public void setPickDate(String pickDate) throws Exception {
        this.pickDate = parseStringToLocalDate(pickDate);
    }

    public LocalDateTime getDropDate() {
        return dropDate;
    }

    public void setDropDate(String dropDate) throws Exception {
        this.dropDate =parseStringToLocalDate(dropDate);
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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

    public LocalDateTime parseStringToLocalDate(String timeString)throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(timeString,formatter);
        return dateTime;
    }
    public String localDateToString(LocalDateTime dateTime)throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

   public Invoice getInvoice() {
       return invoice;
   }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
