package com.example.avia.controller;

import com.example.avia.dto.LoginDto;
import com.example.avia.entity.User;
import com.example.avia.service.AviaServiceImpl;
import com.example.avia.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @Autowired
    private AviaServiceImpl aviaServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<List<String>> loginUser(@RequestBody LoginDto logindto) {
        return new ResponseEntity<>(loginServiceImpl.loginUser(logindto), HttpStatus.OK);
    }

    @GetMapping("/pasttravels")
    public ResponseEntity<Object> getAllPassengerDetails(@RequestParam(value = "userId") Integer userId) {

        User user = aviaServiceImpl.getAllPassengerDetails(userId);
        if (user != null) {
            return new ResponseEntity<>(user.getBookingDetails(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No past travels for this User", HttpStatus.NOT_FOUND);
        }
    }
}
