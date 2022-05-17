package com.example.assignment2.services;

import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public  class BookingService {

    public final BookingRepository bookingRepository;
    public final InvoiceService invoiceService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
        this.bookingRepository = bookingRepository;
    }

    // read

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    // create (add new booking)
    public void addNewBooking(Booking booking) {
        Invoice invoice = new Invoice();
        invoice.setTotalChange(500);
        invoiceService.addInvoice(invoice, booking.getDriverId(), booking.getCustomerId());
        booking.setInvoice(invoice);
        // no condition needed
        bookingRepository.save(booking);
    }

    // update booking
    @Transactional
    public void updateBooking(Long id,
                              Double distance,
                              String end_location,
                              String starting_location,
                              String pickDate, String dropDate,Long invoiceId) throws Exception {

        Booking booking = bookingRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("booking with id " + id + " does not exist")
        );

        if (distance != null &&
                !Objects.equals(distance, booking.getDistance())) {
            booking.setDistance(distance);
        }

        if (end_location != null &&
                !Objects.equals(end_location, booking.getEndLocation())) {
            booking.setEndLocation(end_location);
        }

        if (starting_location != null &&
                !Objects.equals(starting_location, booking.getStartingLocation())) {
            booking.setStartingLocation(starting_location);
        }
        if (pickDate != null &&
                !Objects.equals(pickDate,booking.localDateToString(booking.getPickDate()))) {
            booking.setPickDate(pickDate);
        }
        if (pickDate != null &&
                !Objects.equals(dropDate,booking.localDateToString(booking.getDropDate()))) {
            booking.setDropDate(dropDate);
        }

    }


    // delete booking

    public void deleteBooking(Long id) {

        if (!bookingRepository.existsById(id)) {
            throw new IllegalStateException(
                    "Booking with id" + id + "does not exist");
        }
        // find invoice + delete invoice first
        Optional<Booking> bookingOp = bookingRepository.findById(id);
        if(bookingOp.get().getInvoice() != null){
            invoiceService.deleteInvoice( bookingOp.get().getInvoice().getId());
        }
        // if exist delete booking
        bookingRepository.deleteById(id);
    }
}



