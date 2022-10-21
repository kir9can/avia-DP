package com.example.avia.controller;

import com.example.avia.dto.AviaDetailsDto;
import com.example.avia.dto.AviaPermissionDto;
import com.example.avia.dto.BookingDetailsDto;
import com.example.avia.dto.ResponseData;
import com.example.avia.entity.AviaDetails;
import com.example.avia.exeption.BookMyAviaException;
import com.example.avia.service.AviaBookingService;
import com.example.avia.service.AviaServiceImpl;
import com.example.avia.service.SearchAviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/avia")
public class AviaController {



    @Autowired
    private AviaServiceImpl aviaServiceImpl;

    @Autowired
    private SearchAviaService searchAviaService;

    @Autowired
    private AviaBookingService aviaBookingService;


    @PostMapping("/add")
    public ResponseEntity<Object> addFlight(@RequestBody AviaDetailsDto aviaServiceDto) {

        AviaDetails aviaDetails = aviaServiceImpl.addAvia(new AviaDetailsDto());

        return new ResponseEntity<>(aviaDetails, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<AviaDetails>> searchFlight(@RequestParam String source,
                                                          @RequestParam String destination, @RequestParam String sortparam, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return new ResponseEntity<>(searchAviaService.searchFlight(source, destination, sortparam, localDate),
                HttpStatus.OK);
    }

    @PutMapping("/grant/permission")
    public ResponseEntity<Object> grantAviaPermissions(AviaPermissionDto permission) {
        ResponseData response = AviaServiceImpl.grantAviaPermissions(permission);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/book")
    public ResponseEntity<Object> bookFlight(@RequestBody BookingDetailsDto bookingDetailsDto) {

        try {
            return new ResponseEntity<>(aviaBookingService.bookingAvia(bookingDetailsDto), HttpStatus.ACCEPTED);
        } catch (BookMyAviaException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
