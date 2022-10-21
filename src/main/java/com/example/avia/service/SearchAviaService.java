package com.example.avia.service;

import com.example.avia.entity.AviaDetails;
import com.example.avia.exeption.NotFoundException;
import com.example.avia.repository.AviaDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class SearchAviaService {
    @Autowired
    private AviaDetailsRepository searchAviaRepository;


    public List<AviaDetails> searchFlight(String source, String destination, String sortparam, LocalDate date) {
        String permission = "APPROVED";
        if(sortparam.equalsIgnoreCase("price")) {
            return searchAviaRepository.findBySourceAndDestinationAndDateAndPermissionOrderByPriceAsc(source, destination, date, permission);
        }
        else if(sortparam.equalsIgnoreCase("timeDuration")) {
            return searchAviaRepository.findBySourceAndDestinationAndDateAndPermissionOrderByTimeDurationAsc(source, destination, date, permission);
        }
        else if(sortparam.equalsIgnoreCase("arrivalTime")) {
            return searchAviaRepository.findBySourceAndDestinationAndDateAndPermissionOrderByArrivalTimeAsc(source, destination, date, permission);
        }
        else {
            throw new NotFoundException("Sort Parameter is not specified properly. Please specify out of these <price, timeDuration, arrivalTime>"+sortparam);
        }
    }
}
