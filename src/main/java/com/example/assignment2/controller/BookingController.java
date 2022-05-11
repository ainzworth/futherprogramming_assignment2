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
    // create booking
    @PostMapping
    public void addBooking(@RequestBody Booking booking){
         bookingService.addNewBooking(booking);
    }
    // update
//    @PutMapping(path = "{id}")
//    public void updateBooking(
//            @PathVariable("id")Long id,
//            @RequestParam(required = false) double distance,
//            @RequestParam(required = false) String end_location,
//            @RequestParam(required = false) String starting_location,
//            @RequestParam(required = false) String starting_location,
//            @RequestParam(required = false) String starting_location,
//    ){
//
//    }
    // delete
    @DeleteMapping(path = "{id}")
    public void deleteBooking(@PathVariable("id") Long id){
        bookingService.deleteBooking(id);
    }


}
