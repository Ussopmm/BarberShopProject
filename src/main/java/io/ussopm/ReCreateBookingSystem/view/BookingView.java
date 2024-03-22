package io.ussopm.ReCreateBookingSystem.view;


import io.ussopm.ReCreateBookingSystem.model.Barber;
import io.ussopm.ReCreateBookingSystem.model.Customer;
import io.ussopm.ReCreateBookingSystem.model.ServiceItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingView {

    private int bookingId;

    private LocalDate bookingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String comments;

    @NotBlank
    private String customerName;
    @NotBlank
    private String customerEmail;
    @NotBlank
    private String phoneNumber;
    private Integer barberId;

    private Integer serviceId;
}
