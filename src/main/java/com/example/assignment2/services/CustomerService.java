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

    private final DriverRepository driverRepository;

    private final InvoiceRepository invoiceRepository;
    @Autowired
    CustomerService(CustomerRepository customerRepository,DriverRepository driverRepository,InvoiceRepository invoiceRepository){
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.invoiceRepository = invoiceRepository;
    }

    // read
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    // create
    public void addCustomer(Customer customer) {
        Optional<Customer> cs = customerRepository.findCustomerByCustomer_id(customer.getCustomerNum());
        // if customer id exist throw
        if(cs.isPresent()){
                throw new IllegalStateException(
                        "Customer with customer id " + customer.getCustomerNum() + " already existed"
                );
        }
        // else save customer
        customerRepository.save(customer);
    }
    // update
    @Transactional
    public void updateCustomer(Long customerId, String customerNum, String name) {
        Customer cs = customerRepository.findById(customerId).orElseThrow(
                () -> new IllegalStateException("customer with id " + customerId +" does not exist")
        );
        if(customerNum != null &&
            !Objects.equals(customerNum,cs.getCustomerNum())){
            // check if customerNum is dup
            Optional<Customer> optionalCustomer = customerRepository.findCustomerByCustomer_id(customerNum);
            if(optionalCustomer.isPresent()){
                throw new IllegalStateException("Customer number already exist");
            }
            cs.setCustomer_id(customerNum);
        }
        if(name != null &&
                !Objects.equals(name,cs.getName())){
            cs.setName(name);
        }
    }
        // create invoice
    @Transactional
    public void createInvoice(Long customerId,Long driverId) {
                Invoice invoice = new Invoice();
                Customer cs = customerRepository.findById(customerId).
                        orElseThrow(() -> new IllegalStateException("customer with id "+ customerId +" does not exist")
                );
                Driver driver = driverRepository.findById(driverId).
                        orElseThrow(() -> new IllegalStateException("driver with id "+ driverId +" does not exist")
                        );
                invoice.setCustomer(cs);
                invoice.setDriver(driver);
                cs.getInvoices().add(invoice);
                driver.getInvoices().add(invoice);

                // add invoice to list of invoices in customer and set customer value in invoice, same thing for driver




        }


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
