package io.ussopm.ReCreateBookingSystem.repository;

import io.ussopm.ReCreateBookingSystem.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Integer> {
}
