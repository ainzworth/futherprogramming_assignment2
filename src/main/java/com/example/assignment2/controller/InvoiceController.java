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
    @PostMapping(path = "createInvoice")
    public void addInvoice(
            @RequestBody Invoice invoice
            ){
        invoiceService.addInvoice(invoice,invoice.getDriverId(),invoice.getCustomerId());
    }
    // update
        @PutMapping(path ="update/{invoiceId}")
        public void updateInvoice(
                @PathVariable("invoiceId") Long invoiceId,
                @RequestParam(required = false) Long customerId,
                @RequestParam(required = false) Long driverId,
                @RequestParam(required = false) Double totalChange
        ){
            invoiceService.updateInvoice(invoiceId,customerId,driverId,totalChange);
        }
    // delete
    @DeleteMapping(path = "delete/{id}")
    public void deleteInvoice(
            @PathVariable("id") Long id
    ){
        invoiceService.deleteInvoice(id);
    }

}
