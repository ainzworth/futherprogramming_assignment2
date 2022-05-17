package com.example.assignment2.services;

import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repositories.BookingRepository;
import com.example.assignment2.repositories.CustomerRepository;
import com.example.assignment2.repositories.DriverRepository;
import com.example.assignment2.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import javax.transaction.Transactional;
import java.security.Provider;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;

    private final BookingRepository bookingRepository;

    @Autowired
    InvoiceService(CustomerRepository customerRepository,DriverRepository driverRepository,InvoiceRepository invoiceRepository
    ,BookingRepository bookingRepository){
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.invoiceRepository = invoiceRepository;
        this.bookingRepository = bookingRepository;
    }


    // read
    public List<Invoice> findInvoices(){
        return invoiceRepository.findAll();
    }
    // create
    @Transactional
    public void addInvoice(Invoice invoice,Long driver_id,Long customer_id)
    {
        Customer cs = customerRepository.findById(customer_id).
                orElseThrow(() -> new IllegalStateException("customer with id "+ customer_id +" does not exist")
                );
        Driver driver = driverRepository.findById(driver_id).
                orElseThrow(() -> new IllegalStateException("driver with id "+ driver_id +" does not exist")
                );
        invoice.setDriver(driver);
        invoice.setCustomer(cs);
        cs.getInvoices().add(invoice);
        driver.getInvoices().add(invoice);


    }


    // update
    @Transactional
    public void updateInvoice(Long invoiceId, Long customerId, Long driverId, Double totalChange) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(
                () -> new IllegalStateException("invoice with id " + invoiceId + " does not exist")
        );

        if(customerId != null &&
                !Objects.equals(invoice.getCustomerId(),customerId)){
            // check if customer id exist
            Optional<Customer> newCs = customerRepository.findById(customerId);
            if(newCs.isEmpty()){
                       throw new IllegalStateException("customer with id "+ customerId + " does not exist");
            }


//          find the invoice in the list of invoices in the old customer entity
            for (Iterator<Invoice> it = invoice.getCustomer().getInvoices().iterator(); it.hasNext(); ) {
                Invoice invoiceTemp = it.next();
                if (invoiceTemp.equals(invoice)){
                    // remove the invoice from the old customer
                    invoice.getCustomer().getInvoices().remove(invoice);
                    // set new customer id and customer for invoice
                    invoice.setCustomerId(customerId);
                    invoice.setCustomer(newCs.get());
                    // add the invoice to the new customer
                    newCs.get().getInvoices().add(invoice);
                    break;
                }

            }

        }
        if(driverId != null &&
                !Objects.equals(invoice.getDriverId(),driverId)){
            // check if customer id exist
            Optional<Driver> newDv = driverRepository.findById(driverId);
            if(newDv.isEmpty()){
                throw new IllegalStateException("driver with id "+ driverId + " does not exist");
            }
            //          find the invoice in the list of invoices in the old driver entity
            for (Iterator<Invoice> it = invoice.getDriver().getInvoices().iterator(); it.hasNext(); ) {
                Invoice invoiceTemp = it.next();
                if (invoiceTemp.equals(invoice)){
                    // remove the invoice from the old driver
                    invoice.getCustomer().getInvoices().remove(invoice);
                    // set new driver id and customer for invoice
                    invoice.setDriverId(driverId);
                    invoice.setDriver(newDv.get());
                    // add the invoice to the new driver
                    newDv.get().getInvoices().add(invoice);
                    break;
                }

            }

        }

        if(totalChange != null &&
            !Objects.equals(invoice.getTotalChange(),totalChange)){
                invoice.setTotalChange(totalChange);
        }
    }

    // delete
    @Transactional
    public void deleteInvoice(Long id) {
        Invoice delInvoice = invoiceRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("invoice by id " + id + " does not exist")
        );
        // delete the invoice in the invoices list of customer and driver

        delInvoice.getDriver().getInvoices().remove(delInvoice);
        delInvoice.getCustomer().getInvoices().remove(delInvoice);
        Optional<Booking> optionalBooking = bookingRepository.findBookingByInvoice(delInvoice);

        optionalBooking.ifPresent(
                booking -> booking.setInvoice(null)
        );


    }


}
