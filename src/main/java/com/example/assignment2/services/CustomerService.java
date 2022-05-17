package com.example.assignment2.services;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repositories.CustomerRepository;
import com.example.assignment2.repositories.DriverRepository;
import com.example.assignment2.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service

public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    // read
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
        // get customer by name
        public List<Customer> getCustomersByName(String name) {
            return customerRepository.findAllCustomerByName(name);
        }
        // get customer by address
        public List<Customer> getCustomersByAddress(String address) {
            return  customerRepository.findAllCustomerByAddress(address);
        }
        // get customer by phone

        public List<Customer> getCustomersByPhone(String phone) {
            return customerRepository.findAllCustomerByPhone(phone);
        }
    // create
    public void addCustomer(Customer customer) {
        Optional<Customer> cs = customerRepository.findCustomerByPhone(customer.getPhone());
        // if customer id exist throw
        if(cs.isPresent()){
                throw new IllegalStateException(
                        "Customer with Phone " + customer.getPhone() + " already existed"
                );
        }
        // else save customer
        customerRepository.save(customer);
    }
    // update
    @Transactional
    public void updateCustomer(Long customerId, String address,String phone, String name) {
        Customer cs = customerRepository.findById(customerId).orElseThrow(
                () -> new IllegalStateException("customer with id " + customerId +" does not exist")
        );
        if(address != null &&
            !Objects.equals(address,cs.getAddress())){
            cs.setAddress(address);
        }
        if(phone != null &&
            !Objects.equals(phone,cs.getPhone())){
            // check if customerNum is dup
            Optional<Customer> optionalCustomer = customerRepository.findCustomerByPhone(phone);
            if(optionalCustomer.isPresent()){
                throw new IllegalStateException("Customer number already exist");
            }
            cs.setPhone(phone);
        }
        if(name != null &&
                !Objects.equals(name,cs.getName())){
            cs.setName(name);
        }
    }
        // create invoice



    // delete
    public void deleteCustomerById(Long id){
        Optional<Customer> cs = customerRepository.findById(id);
        // if not exist throw
        if(cs.isEmpty()){
            throw new IllegalStateException(
                    "Customer with id" + id + "does not exist"
            );
        }

        // delete customer
        customerRepository.delete(cs.get());

    }



}
