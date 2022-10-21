package com.example.avia.service;

import com.example.avia.dto.BookingDetailsDto;
import com.example.avia.exeption.BookMyAviaException;

public interface AviaBookingService {

    public String bookingAvia(BookingDetailsDto bookingDetailsDto) throws BookMyAviaException;}

