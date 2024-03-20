package io.ussopm.ReCreateBookingSystem.service;


import io.ussopm.ReCreateBookingSystem.model.Barber;
import io.ussopm.ReCreateBookingSystem.model.Booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BarberService {
    List<Barber> getAllBarbers();

}
