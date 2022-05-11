package com.example.assignment2.services;

import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Provider;
import java.util.List;

@Service
@Transactional
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    // read
    public List<Invoice> findInvoices(){
        return invoiceRepository.findAll();
    }
    // create
    public void addInvoice(Invoice invoice){
        invoiceRepository.save(invoice);
    }
    // update
        // add later
    // delete
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
