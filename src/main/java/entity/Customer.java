package entity;

import org.springframework.beans.factory.annotation.Autowired;

public class Customer {

    private Long id;
    private String name;
    @Autowired
    private Booking booking;
    public Customer(){

    }

    public Customer(Long id, String name, Booking booking) {
        this.id = id;
        this.name = name;
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
