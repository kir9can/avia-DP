package com.example.avia.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String username;
    private String password;
    @Column(name = "mobile_number")
    private String mobilNumber;
    @Column(name = "email_id")
    private String emailId;
    @OneToMany(mappedBy = "bookedBy")
    private List<BookingDetails> bookingDetails = new ArrayList<>();
    private String role;
}
