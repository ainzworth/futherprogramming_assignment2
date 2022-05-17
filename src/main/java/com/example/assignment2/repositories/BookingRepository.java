package com.example.assignment2.repositories;

import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookingRepository extends JpaRepository<Booking,Long>{
    @Query("SELECT b FROM Booking b where b.pickDate > ?1 and b.dropDate < ?2")
    List<Booking> findBookingByPeriod(LocalDateTime start, LocalDateTime end);

    @Query("SELECT b FROM Booking b where b.invoice = ?1")
    Optional<Booking> findBookingByInvoice(Invoice invoice);

}

