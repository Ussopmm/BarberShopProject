package io.ussopm.ReCreateBookingSystem.service;

import io.ussopm.ReCreateBookingSystem.model.ServiceItem;

import java.util.List;
import java.util.Optional;

public interface ServiceItemService {
    List<ServiceItem> getAllServices();

    Optional<ServiceItem> getServiceById(Integer serviceId);
}
