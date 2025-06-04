package com.data.ss17.controlller;

import com.data.ss17.model.Customer;
import com.data.ss17.model.Orders;
import com.data.ss17.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {
    @Autowired
    private OrderRepository orderService;

    private boolean checkAdminAccess(HttpSession session) {
        Customer user = (Customer) session.getAttribute("currentUser");
        return user != null && "ADMIN".equals(user.getRole());
    }

    @GetMapping
    public String listOrders(HttpSession session,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String search,
                             @RequestParam(required = false) String status,
                             @RequestParam(required = false) String startDate,
                             @RequestParam(required = false) String endDate,
                             Model model) {
        if (!checkAdminAccess(session)) return "redirect:/login";

        if (search != null && (search.trim().isEmpty() || "null".equals(search))) {
            search = null;
        }
        if (status != null && (status.trim().isEmpty() || "null".equals(status))) {
            status = null;
        }
        if (startDate != null && (startDate.trim().isEmpty() || "null".equals(startDate))) {
            startDate = null;
        }
        if (endDate != null && (endDate.trim().isEmpty() || "null".equals(endDate))) {
            endDate = null;
        }
        List<Orders> orders = orderService.findAllWithFilter(page, size, search, status, startDate, endDate);
        long total = orderService.countWithFilter(search, status, startDate, endDate);
        int totalPages = (int) Math.ceil((double) total / size);

        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);
        model.addAttribute("status", status);
        model.addAttribute("content", "admin/orders");
        return "admin/admin-layout";
    }

    @PostMapping("/update-status/{id}")
    public String updateOrderStatus(@PathVariable Long id,
                                    @RequestParam String status,
                                    HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/login";
        Orders order = orderService.findById(id);
        if (order != null) {
            order.setStatus(status);
            orderService.update(order);
        }
        return "redirect:/admin/orders";
    }
}