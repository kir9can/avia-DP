package com.example.avia.controller;

import com.example.avia.service.AviaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private LoginServiceImpl loginserviceimpl;

    @Autowired
    private AviaServiceImpl aviaServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<List<String>> loginUser(@RequestBody LoginDto logindto) {
        return new ResponseEntity<>(loginserviceimpl.loginUser(logindto), HttpStatus.OK);
    }

    @GetMapping("/pasttravels")
    public ResponseEntity<Object> getAllPassengerDetails(@RequestParam(value = "userId") Integer userId) {

        User user = flightServiceImpl.getAllPassengerDetails(userId);
        if (user != null) {
            return new ResponseEntity<>(user.getBookingDetails(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No past travels for this User", HttpStatus.NOT_FOUND);
        }
    }
}
