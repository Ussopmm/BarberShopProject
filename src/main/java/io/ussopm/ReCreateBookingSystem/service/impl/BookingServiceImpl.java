package io.ussopm.ReCreateBookingSystem.service.impl;

import io.ussopm.ReCreateBookingSystem.model.Booking;
import io.ussopm.ReCreateBookingSystem.repository.BookingRepository;
import io.ussopm.ReCreateBookingSystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    @Value("${app.service.opening-hour}")
    private String openingTime;
    @Value("${app.service.closing-hour}")
    private String closingTime;
    @Value("${app.service.duration}")
    private String duration;

    @Override
    public List<LocalTime> getAvailableBarberByDateAndTime(String dateTimeString, Integer barberId) {
        List<LocalTime> availableTimeList = new ArrayList<>();
        LocalDate dateTime = dateTimeString == null ? LocalDate.now() : LocalDate.parse(dateTimeString);
        LocalTime startTime = LocalTime.parse(openingTime);
        LocalTime endTime = LocalTime.parse(closingTime);
        long durationTime = Long.parseLong(duration);

        LocalTime tempTime = startTime;
        while (endTime.compareTo(tempTime) != 0) {
            List<Booking> bookings = bookingRepository.findByBookingDateAndStartTimeAndBarberId(dateTime, startTime, barberId);

            if (bookings.isEmpty())
                availableTimeList.add(tempTime);
            tempTime = tempTime.plusMinutes(durationTime);
        }

        return availableTimeList;
    }



}
