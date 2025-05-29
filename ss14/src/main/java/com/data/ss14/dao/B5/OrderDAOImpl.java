package com.data.ss14.dao.B5;
import com.data.ss14.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void updateOrder(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(order.getId())) {
                orders.set(i, order);
                return;
            }
        }
    }

    @Override
    public void deleteOrder(String id) {
        orders.removeIf(o -> o.getId().equals(id));
    }

    @Override
    public Order getOrderById(String id) {
        return orders.stream().filter(o -> o.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }
}