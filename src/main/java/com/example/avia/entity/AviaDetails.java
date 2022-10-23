package com.example.avia.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "avia_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "flightId")
@JsonIgnoreProperties({"hibernateLazyInitialaizer", "handler"})
public class AviaDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avia_id")
    private Long aviaId;

    private String source;

    private String destination;

    private LocalDate date;

    @Column(name = "departure_time")
    private String departureTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @Column(name = "time_duration")
    private String timeDuration;

    private double price;

    @Column(name = "avialaible_seats")
    private int availableSeats;

    private String permission;
}
