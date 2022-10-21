package com.example.avia.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookingDetails {

    private Long bookingId;

    private LocalDateTime bookingDateTime;

    private Double totalAmount;


    private AviaDetails aviaDetails;


    private User bookedBy;


    private List<PassengerDetails> passengerDetails = new ArrayList<>();
}
