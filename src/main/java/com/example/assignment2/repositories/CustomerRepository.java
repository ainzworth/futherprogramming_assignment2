package com.example.assignment2.repositories;

import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("select cs from Customer cs where cs.phone = ?1")
    Optional<Customer> findCustomerByPhone(String phone);
    @Query("select cs from Customer cs where cs.name = ?1")
    List<Customer> findAllCustomerByName(String name);

    @Query("select cs from Customer cs where cs.phone = ?1")
    List<Customer> findAllCustomerByPhone(String phone);

    @Query("select cs from Customer cs where cs.address = ?1")
    List<Customer> findAllCustomerByAddress(String address);

}
