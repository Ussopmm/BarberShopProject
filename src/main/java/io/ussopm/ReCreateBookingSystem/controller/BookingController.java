package io.ussopm.ReCreateBookingSystem.controller;

import io.ussopm.ReCreateBookingSystem.model.Barber;
import io.ussopm.ReCreateBookingSystem.model.Booking;
import io.ussopm.ReCreateBookingSystem.model.ServiceItem;
import io.ussopm.ReCreateBookingSystem.service.BarberService;
import io.ussopm.ReCreateBookingSystem.service.BookingService;
import io.ussopm.ReCreateBookingSystem.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                                        @RequestParam(required = false) String dateTimeString, Model model) {
        List<LocalTime> availableTimeList = bookingService.getAvailableBarberByDateAndTime(dateTimeString, barberId);

        model.addAttribute("service", serviceItemService.getServiceById(serviceId));
        model.addAttribute("barber", barberId);
        model.addAttribute("availableTime", availableTimeList);

        return "select_date_time";
    }
}
