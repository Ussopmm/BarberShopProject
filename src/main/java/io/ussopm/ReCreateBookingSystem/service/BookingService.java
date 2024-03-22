package io.ussopm.ReCreateBookingSystem.service;

import io.ussopm.ReCreateBookingSystem.model.Booking;
import io.ussopm.ReCreateBookingSystem.view.BookingView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    List<LocalTime> getAvailableBarberByDateAndTime(String dateTime, Integer barberId);

    void save(BookingView bookingView);
}

