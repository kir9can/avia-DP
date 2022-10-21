package com.example.avia.service;

import com.example.avia.dto.BookingDetailsDto;
import com.example.avia.dto.PassengerDetailsDto;
import com.example.avia.entity.AviaDetails;
import com.example.avia.entity.BookingDetails;
import com.example.avia.entity.PassengerDetails;
import com.example.avia.entity.User;
import com.example.avia.exeption.BookMyAviaException;
import com.example.avia.repository.AviaDetailsRepository;
import com.example.avia.repository.BookingDetailsRepository;
import com.example.avia.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AviaBookingServiceImpl implements AviaBookingService{

    @Autowired
    private AviaDetailsRepository aviaDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Override
    public String bookingAvia(BookingDetailsDto bookingDetailsDto) throws BookMyAviaException {
        Optional<AviaDetails> flightDetails = aviaDetailsRepository.findById(bookingDetailsDto.getAviaId());
        Optional<User> userDetails = userRepository.findById(bookingDetailsDto.getBookedBy());
        if (userDetails.isPresent() && flightDetails.isPresent()) {
            if(flightDetails.get().getAvailableSeats() < bookingDetailsDto.getPassengerDetails().size()) {
                throw new BookMyAviaException("Seats Not Available. Please try another flight");
            }
            AviaDetails details = flightDetails.get();
            details.setAvailableSeats(details.getAvailableSeats() - bookingDetailsDto.getPassengerDetails().size());
            BookingDetails bookingDetails = new BookingDetails();
            bookingDetails.setAviaDetails(details);
            bookingDetails.setBookedBy(userDetails.get());
            bookingDetails.setBookingDateTime(LocalDateTime.now());

            List<PassengerDetails> passengerDetails = new ArrayList<>();
            for (PassengerDetailsDto dto : bookingDetailsDto.getPassengerDetails()) {
                PassengerDetails passengerDetail = new PassengerDetails();
                passengerDetail.setBookingDetails(bookingDetails);
                BeanUtils.copyProperties(dto, passengerDetail);
                passengerDetails.add(passengerDetail);

            }

            bookingDetails.setPassengerDetails(passengerDetails);
            bookingDetails.setTotalAmount(flightDetails.get().getPrice() * passengerDetails.size());
            aviaDetailsRepository.save(details);
            return bookingDetailsRepository.save(bookingDetails).getBookingId().toString();

        }
        throw new BookMyAviaException("Invalid UserId or Flight Id ");
    }
}
