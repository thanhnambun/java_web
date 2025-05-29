package com.data.ss14.service.bt5;
import com.data.ss14.dao.B5.OrderDAO;
import com.data.ss14.dao.B5.OrderDAOImpl;
import com.data.ss14.model.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderDAO.deleteOrder(id);
    }

    @Override
    public Order getOrderById(String id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public List<Order> getOrders() {
        return orderDAO.getAllOrders();
    }
}