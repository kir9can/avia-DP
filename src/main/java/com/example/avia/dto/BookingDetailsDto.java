package com.example.avia.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingDetailsDto {
    private int bookedBy;
    private Long aviaId;
    List<PassengerDetailsDto> passengerDetails;
}
