package io.ussopm.ReCreateBookingSystem.repository;

import io.ussopm.ReCreateBookingSystem.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceItemRepository extends JpaRepository<ServiceItem, Integer> {
}
