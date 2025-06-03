package com.data.ss18.repository;

import com.data.ss17.model.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);
    boolean save(Customer customer);
    void update(Customer customer);
    Customer findById(Long id);
}
