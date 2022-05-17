package com.example.assignment2.controller;

import com.example.assignment2.entity.Booking;
import com.example.assignment2.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    // read(get all bookings)
    @GetMapping
    public List<Booking> getBookings(){
        return bookingService.getBookings();
    }
        // read booking by date
    // create booking
    @PostMapping(path = "create")
    public void addBooking(@RequestBody Booking booking){
         bookingService.addNewBooking(booking);
    }

    // update
    @PutMapping(path = "update/{id}")
    public void updateBooking(
            @PathVariable("id")Long id,
            @RequestParam(required = false) Double distance,
            @RequestParam(required = false) String end_location,
            @RequestParam(required = false) String starting_location,
            @RequestParam(required = false) String pickDate,
            @RequestParam(required = false) String dropDate,
            @RequestParam(required = false) Long invoiceId

            ) throws Exception {
        bookingService.updateBooking(id,distance,end_location,starting_location,pickDate,dropDate,invoiceId);
    }
    // delete
    @DeleteMapping(path = "delete/{id}")
    public void deleteBooking(@PathVariable("id") Long id){
        bookingService.deleteBooking(id);
    }


}
