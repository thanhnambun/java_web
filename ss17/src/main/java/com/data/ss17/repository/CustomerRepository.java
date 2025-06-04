package com.data.ss17.repository;

import com.data.ss17.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);
    boolean save(Customer customer);
    void update(Customer customer);
    Customer findById(Long id);
    List<Customer> findAllWithPagination(int page, int size, String search);
    Long countWithSearch(String search);
    Long countAll();
}
