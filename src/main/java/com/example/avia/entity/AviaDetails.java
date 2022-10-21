package com.example.avia.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AviaDetails {
    private Long aviaId;
    private String source;
    private String destination;
    private LocalDate date;
    private String departureTime;
    private String arrivalTime;
    private String timeDuration;
    private double price;
    private int availableSeats;
    private String permission;
}
