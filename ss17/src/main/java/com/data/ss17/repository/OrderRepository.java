package com.data.ss17.repository;

import com.data.ss17.model.Orders;

import java.util.List;

public interface OrderRepository {
    void save(Orders order);
    List<Orders> findByCustomerIdWithPagination(Long customerId, int page, int size);
    Long countByCustomerId(Long customerId);
    Orders findById(Long id);
    void update(Orders order);
}
