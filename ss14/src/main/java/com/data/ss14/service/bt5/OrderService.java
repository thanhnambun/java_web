package com.data.ss14.service.bt5;

import com.data.ss14.model.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(String id);
    Order getOrderById(String id);
    List<Order> getOrders();
}