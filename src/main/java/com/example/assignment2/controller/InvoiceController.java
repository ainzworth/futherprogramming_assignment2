package com.example.assignment2.controller;


import com.example.assignment2.entity.Invoice;
import com.example.assignment2.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
    // read
    @GetMapping
    public List<Invoice> getInvoices(){
        return invoiceService.findInvoices();
    }
    // create
    @PostMapping
    public void addInvoice(@RequestBody Invoice invoice){
        invoiceService.addInvoice(invoice);
    }
    // update
        // add later
    // delete
    @DeleteMapping(path = "{id}")
    public void deleteInvoice(
            @PathVariable("id") Long id
    ){
        invoiceService.deleteInvoice(id);
    }

}
