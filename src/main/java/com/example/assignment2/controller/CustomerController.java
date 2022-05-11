package com.example.assignment2.controller;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.repositories.CustomerRepository;
import com.example.assignment2.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // read
    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    // create
    @PostMapping
    public void addCustomer(
            @RequestBody Customer customer
    ){
        customerService.addCustomer(customer);
    }
    // update
    @PutMapping(path = "update/{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String customerNum,
            @RequestParam(required = false) String name
    ){
        customerService.updateCustomer(customerId,customerNum,name);
    }
      // add later

        @PostMapping(path = "createInvoice/{customerId}/{driverId}")
        public void addInvoice(
                @PathVariable("customerId") Long customerId,
                @PathVariable("driverId") Long driverId
        ){
            customerService.createInvoice(customerId,driverId);
        }

    // delete
    @DeleteMapping(path = "{id}")
    public void deleteCustomer(
            @PathVariable("id") Long id
    ){
        customerService.deleteCustomerById(id);
    }
}
