package io.ussopm.ReCreateBookingSystem.service.impl;

import io.ussopm.ReCreateBookingSystem.model.Barber;
import io.ussopm.ReCreateBookingSystem.model.Booking;
import io.ussopm.ReCreateBookingSystem.model.Customer;
import io.ussopm.ReCreateBookingSystem.model.ServiceItem;
import io.ussopm.ReCreateBookingSystem.repository.BarberRepository;
import io.ussopm.ReCreateBookingSystem.repository.BookingRepository;
import io.ussopm.ReCreateBookingSystem.repository.CustomerRepository;
import io.ussopm.ReCreateBookingSystem.repository.ServiceItemRepository;
import io.ussopm.ReCreateBookingSystem.service.BarberService;
import io.ussopm.ReCreateBookingSystem.service.BookingService;
import io.ussopm.ReCreateBookingSystem.service.CustomerService;
import io.ussopm.ReCreateBookingSystem.service.ServiceItemService;
import io.ussopm.ReCreateBookingSystem.view.BookingView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ServiceItemService serviceItemService;
    private final BarberService barberService;
    private final CustomerService customerService;
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
            List<Booking> bookings = bookingRepository.findByBookingDateAndStartTimeAndBarberId(dateTime, tempTime, barberId);

            if (bookings.isEmpty())
                availableTimeList.add(tempTime);
            tempTime = tempTime.plusMinutes(durationTime);
        }

        return availableTimeList;
    }

    @Override
    public void save(BookingView bookingView) {
        Customer customer = Customer.builder()
                .name(bookingView.getCustomerName())
                .email(bookingView.getCustomerEmail())
                .phoneNumber(bookingView.getPhoneNumber())
                .build();
        customer = customerService.save(customer);

        Optional<ServiceItem> service = serviceItemService.getServiceById(bookingView.getServiceId());
        Optional<Barber> barber = barberService.getBarberById(bookingView.getBarberId());

        Booking booking = Booking.builder()
                .bookingDate(bookingView.getBookingDate())
                .startTime(bookingView.getStartTime())
                .endTime(bookingView.getStartTime().plusMinutes(30))
                .comments(bookingView.getComments())
                .barber(barber.get())
                .customer(customer)
                .serviceItem(service.get())
                .build();
        bookingRepository.save(booking);
    }


}
