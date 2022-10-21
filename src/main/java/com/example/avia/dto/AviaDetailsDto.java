package com.example.avia.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AviaDetailsDto {

    private String source;


    private String destination;


    private LocalDate date;


    private String departureTime;


    private String arrivalTime;


    private String timeDuration;


    private double price;

    private int availableSeats;
}
