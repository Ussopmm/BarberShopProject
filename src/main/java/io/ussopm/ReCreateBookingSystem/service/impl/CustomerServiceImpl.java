package io.ussopm.ReCreateBookingSystem.service.impl;

import io.ussopm.ReCreateBookingSystem.model.Customer;
import io.ussopm.ReCreateBookingSystem.repository.CustomerRepository;
import io.ussopm.ReCreateBookingSystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
