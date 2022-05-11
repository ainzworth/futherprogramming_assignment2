package com.example.assignment2.entity;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table
public class Invoice {

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
            name = "customerId",
            insertable = false,
            updatable = false,
            referencedColumnName = "customerId"
    )
    private Customer customer;
    @ManyToOne
    @JoinColumn(
            name = "driverId",
            insertable = false,
            updatable = false,
            referencedColumnName = "driverId"
    )
    private Driver driver;
    private double totalChange;

    @OneToOne(mappedBy = "invoice")
    private Booking booking;
    public Invoice(){};
    public Invoice(Driver driver,Customer customer) {
        this.driver = driver;
        this.customer = customer;
        this.totalChange = 100;
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
}
