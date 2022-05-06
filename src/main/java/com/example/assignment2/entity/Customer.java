package com.example.assignment2.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Collection;
//@Entity
//@Table(name = "customer")
public class Customer {
//    @Id
//    @GeneratedValue()
    private Long id;
    private String name;
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Collection<Booking> booking;
    public Customer(){

    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
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


}
