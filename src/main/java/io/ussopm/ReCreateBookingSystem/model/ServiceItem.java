package io.ussopm.ReCreateBookingSystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "service")
@Data
public class ServiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "serviceItems")
    private List<Barber> barbers;

    @OneToMany(mappedBy = "serviceItem")
    private List<Booking> bookings;
}
