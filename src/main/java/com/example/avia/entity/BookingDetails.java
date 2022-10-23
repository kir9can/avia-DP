package com.example.avia.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "booking_deails")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bookingId")
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;
    @Column(name = "booking_date_time")
    private LocalDateTime bookingDateTime;
    @Column(name = "total_amount")
    private Double totalAmount;

    @OneToOne
    @JoinColumn(name = "flight_id")
    private AviaDetails aviaDetails;

    @ManyToOne
    @JoinColumn(name = "booked_by")
    private User bookedBy;

    @OneToMany(mappedBy = "bookingDetails", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PassengerDetails> passengerDetails = new ArrayList<>();
}
