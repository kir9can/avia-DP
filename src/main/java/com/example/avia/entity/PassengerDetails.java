package com.example.avia.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "passenger_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "passengerId")
public class PassengerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerId;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "passenger_age")
    private int passengerAge;
    @Column(name = "passenger_gender")
    private String passengerGender;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingDetails bookingDetails;
}
