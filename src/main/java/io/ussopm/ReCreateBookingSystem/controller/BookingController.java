package io.ussopm.ReCreateBookingSystem.controller;

import io.ussopm.ReCreateBookingSystem.model.Barber;
import io.ussopm.ReCreateBookingSystem.model.Booking;
import io.ussopm.ReCreateBookingSystem.model.ServiceItem;
import io.ussopm.ReCreateBookingSystem.service.BarberService;
import io.ussopm.ReCreateBookingSystem.service.BookingService;
import io.ussopm.ReCreateBookingSystem.service.ServiceItemService;
import io.ussopm.ReCreateBookingSystem.view.BookingView;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookingController {

    private final ServiceItemService serviceItemService;
    private final BarberService barberService;
    private final BookingService bookingService;
    @GetMapping
    private String mainPage(Model model) {

        model.addAttribute("services", serviceItemService.getAllServices());
        return "select_service";
    }

    @GetMapping("/barbers")
    private String barbersPage(@RequestParam Integer serviceId, Model model) {

        model.addAttribute("barbers", barberService.getAllBarbers());
        model.addAttribute("serviceId", serviceId);
        return "select_barber";
    }

    @GetMapping("/order")
    private String bookingTimeForBarber(@RequestParam Integer serviceId, @RequestParam Integer barberId,
                                        @RequestParam(required = false) String dateTime, Model model) {
        List<LocalTime> availableTimeList = bookingService.getAvailableBarberByDateAndTime(dateTime, barberId);

        model.addAttribute("service", serviceItemService.getServiceById(serviceId));
        model.addAttribute("barber", barberId);
        model.addAttribute("availableTime", availableTimeList);

        return "select_date_time";
    }

    @GetMapping("/customer-info")
    private String savingCustomerForBooking(@RequestParam Integer serviceId, @RequestParam Integer barberId,
                                            @RequestParam(required = false) String dateTime,
                                            @RequestParam String time, Model model) {
        model.addAttribute("service", serviceItemService.getServiceById(serviceId));
        model.addAttribute("barber", barberService.getBarberById(barberId));
        model.addAttribute("date", dateTime);
        model.addAttribute("time", time);
        model.addAttribute("bookingView", new BookingView());
        return "customer_booking_form";
    }

    @PostMapping("/save")
    private String bookingCustomerForBarber(@ModelAttribute("bookingView") BookingView bookingView, Model model) {
        model.addAttribute("service", serviceItemService.getServiceById(bookingView.getServiceId()));
        model.addAttribute("barber", barberService.getBarberById(bookingView.getBarberId()));
        model.addAttribute("dateTime", bookingView.getBookingDate());
        model.addAttribute("time", bookingView.getStartTime());

        bookingService.save(bookingView);
        return "booking_page";
    }
}
