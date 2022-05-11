package com.example.assignment2.repositories;

import com.example.assignment2.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking,Long>{

}

