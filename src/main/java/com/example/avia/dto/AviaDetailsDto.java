package com.example.avia.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AviaDetailsDto {
    @NotNull
    @NotBlank
    private String source;

    @NotNull
    @NotBlank
    private String destination;

    @NotNull
    private LocalDate date;

    @NotNull
    @NotBlank
    private String departureTime;

    @NotNull
    @NotBlank
    private String arrivalTime;

    @NotNull
    @NotBlank
    private String timeDuration;

    @NotNull
    private double price;

    @NotNull
    private int availableSeats;
}
