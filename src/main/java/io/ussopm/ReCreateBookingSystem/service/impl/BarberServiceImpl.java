package io.ussopm.ReCreateBookingSystem.service.impl;

import io.ussopm.ReCreateBookingSystem.model.Barber;
import io.ussopm.ReCreateBookingSystem.model.Booking;
import io.ussopm.ReCreateBookingSystem.repository.BarberRepository;
import io.ussopm.ReCreateBookingSystem.repository.BookingRepository;
import io.ussopm.ReCreateBookingSystem.service.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarberServiceImpl implements BarberService {
    private final BarberRepository barberRepository;
    private final BookingRepository bookingRepository;
    @Override
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }


}
