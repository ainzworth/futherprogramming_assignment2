package com.example.assignment2.services;

import com.example.assignment2.entity.Booking;
import com.example.assignment2.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public  class BookingService{

    public final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    // read

    public List<Booking> getBookings(){
        return  bookingRepository.findAll();
    }

    // create (add new booking)
    public void addNewBooking(Booking booking){
        // no condition needed
        bookingRepository.save(booking);
    }
    // update booking
    // delete booking

    public void deleteBooking(Long id){

        if(!bookingRepository.existsById(id)){
            throw new IllegalStateException(
                    "Booking with id" + id + "does not exist");
        }
        // if exist delete booking
        bookingRepository.deleteById(id);
    }





}