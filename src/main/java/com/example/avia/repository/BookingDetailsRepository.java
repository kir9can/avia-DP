package com.example.avia.repository;

import com.example.avia.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
}
