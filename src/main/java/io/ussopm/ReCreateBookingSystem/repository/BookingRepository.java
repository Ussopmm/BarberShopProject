package io.ussopm.ReCreateBookingSystem.repository;

import io.ussopm.ReCreateBookingSystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByBookingDateAndStartTimeAndBarberId(LocalDate dateTime, LocalTime startTime, Integer barberId);
}
