package io.ussopm.ReCreateBookingSystem.service.impl;

import io.ussopm.ReCreateBookingSystem.model.ServiceItem;
import io.ussopm.ReCreateBookingSystem.repository.ServiceItemRepository;
import io.ussopm.ReCreateBookingSystem.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService {

    private final ServiceItemRepository serviceItemRepository;
    @Override
    public List<ServiceItem> getAllServices() {
        return serviceItemRepository.findAll();
    }

    @Override
    public Optional<ServiceItem> getServiceById(Integer serviceId) {
        return serviceItemRepository.findById(serviceId);
    }
}
