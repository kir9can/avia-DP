package com.example.avia.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class User {
    private int userId;
    private String username;
    private String mobilNumber;
    private String emailId;
    private List<BookingDetails> bookingDetails = new ArrayList<>();
    private String role;
}
