package com.example.avia.entity;

import lombok.Data;

@Data
public class PassengerDetails {
    private int passengerId;
    private int passengerAge;
    private String passengerName;
    private String passengerGender;
    private BookingDetails bookingDetails;
}
