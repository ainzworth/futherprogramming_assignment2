package com.example.assignment2.entity;

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
    private String customerNum;
    private String name;

    @OneToMany(mappedBy = "customer",  cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Invoice> invoices = new HashSet<>();



    public Customer(){

    }

    public Customer(String customer_id, String name) {
        this.customerNum = customer_id;
        this.name = name;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomer_id(String customerNum) {
        this.customerNum = customerNum;
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

    @Transactional
    public void addInvoice(Invoice invoice){
        Set<Invoice> newInvoices = this.invoices;
        invoice.setCustomer(this);
        newInvoices.add(invoice);
        setInvoices(newInvoices);

    }



}
