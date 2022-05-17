package com.example.assignment2.controller;

import com.example.assignment2.dto.APIResponse;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.repositories.CustomerRepository;
import com.example.assignment2.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    public APIResponse<List<Customer>> getCustomers(){
        List<Customer> allCustomers = customerService.getCustomers();
        return new APIResponse<>(allCustomers.size(),allCustomers);
    }
        // show customer by name
        @GetMapping(path = "name/{name}")
        public List<Customer> getCustomersByName(
                @PathVariable("name") String name
        ){
            return customerService.getCustomersByName(name);
        }
        // show customer by phone
        @GetMapping(path = "phone/{phone}")
        public List<Customer> getCustomersByPhone(
                @PathVariable("phone") String phone
        ){
            return customerService.getCustomersByPhone(phone);
        }
        // show customer by address
        @GetMapping(path = "address/{address}")
        public List<Customer> getCustomersByAddress(
                @PathVariable("address") String address
        ){
            return customerService.getCustomersByAddress(address);
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
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String name
    ){
        customerService.updateCustomer(customerId,address,phone,name);
    }

        // create invoice


    // delete
    @DeleteMapping(path = "{id}")
    public void deleteCustomer(
            @PathVariable("id") Long id
    ){
        customerService.deleteCustomerById(id);
    }
}
