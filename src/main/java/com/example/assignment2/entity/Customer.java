package com.example.assignment2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long customerId;
    @Column(
            nullable = false
    )
    private String name;

    private String phone;

    private String address;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(
            name = "customer_id")
    private Set<Invoice> invoices = new HashSet<>();



    public Customer(){

    }

    public Customer(String address,String phone, String name) {
        this.address = address;
        this.phone = phone;
        this.name = name;
    }



    public Long getId() {
        return customerId;
    }

    public void setId(Long id) {
        this.customerId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Invoice> getInvoices(){
        return this.invoices;
    }
    public void setInvoices(Set<Invoice> invoices){
        this.invoices = invoices;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Transactional
    public void addInvoice(Invoice invoice){
        Set<Invoice> newInvoices = this.invoices;
        invoice.setCustomer(this);
        newInvoices.add(invoice);
        setInvoices(newInvoices);

    }



}
