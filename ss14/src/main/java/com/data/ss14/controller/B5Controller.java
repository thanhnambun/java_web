package com.data.ss14.controller;

import com.data.ss14.model.Order;
import com.data.ss14.service.bt5.OrderService;
import com.data.ss14.service.bt5.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
public class B5Controller {

    private OrderService getService(HttpSession session) {
        OrderService service = (OrderService) session.getAttribute("orderService");
        if (service == null) {
            service = new OrderServiceImpl();
            session.setAttribute("orderService", service);
        }
        return service;
    }

    @GetMapping("/B5")
    public String list(Model model, HttpSession session) {
        model.addAttribute("orders", getService(session).getOrders());
        return "/B5/orderList";
    }

    @GetMapping("/order")
    public String form(Model model) {
        model.addAttribute("order", new Order());
        return "/B5/orderForm";
    }

    @PostMapping("/order")
    public String add(@ModelAttribute Order order, HttpSession session) {
        getService(session).addOrder(order);
        return "redirect:/B5";
    }

    @GetMapping("/order/edit/{id}")
    public String editForm(@PathVariable String id, Model model, HttpSession session) {
        Order order = getService(session).getOrderById(id);
        model.addAttribute("order", order);
        return "/B5/orderForm";
    }

    @PostMapping("/order/update")
    public String update(@ModelAttribute Order order, HttpSession session) {
        getService(session).updateOrder(order);
        return "redirect:/B5";
    }

    @GetMapping("/order/delete/{id}")
    public String delete(@PathVariable String id, HttpSession session) {
        getService(session).deleteOrder(id);
        return "redirect:/B5";
    }
}