package io.ussopm.ReCreateBookingSystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "barber")
@Data
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "service_provider",
    joinColumns = @JoinColumn(name = "barber_id"),
    inverseJoinColumns = @JoinColumn(name = "service_id"))
    List<ServiceItem> serviceItems;

    @OneToMany(mappedBy = "barber")
    private List<Booking> bookings;
}
