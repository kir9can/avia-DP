package com.example.avia.repository;

import com.example.avia.entity.AviaDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AviaDetailsRepository extends JpaRepository<AviaDetails, Long> {
    public List<AviaDetails> findBySourceAndDestinationAndDate(String source, String destination, LocalDate date);

    public List<AviaDetails> findBySourceAndDestinationAndDateAndPermissionOrderByPriceAsc(String source, String destination, LocalDate date, String permission);
    public List<AviaDetails> findBySourceAndDestinationAndDateAndPermissionOrderByTimeDurationAsc(String source, String destination, LocalDate date, String permission);
    public List<AviaDetails> findBySourceAndDestinationAndDateAndPermissionOrderByArrivalTimeAsc(String source, String destination, LocalDate date, String permission);

    public List<AviaDetails> findByPermission(String permission);
}
