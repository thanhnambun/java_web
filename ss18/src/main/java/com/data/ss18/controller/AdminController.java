package com.data.ss18.controller;

import com.data.ss18.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class AdminController {
    @Autowired
    private CustomerRepository customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    // Kiểm tra quyền admin
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("customerRole");
        return "ADMIN".equals(role);
    }

    // Trang admin chính
    @GetMapping("/admin")
    public String adminDashboard(Model model, HttpSession session,
                                 @RequestParam(defaultValue = "dashboard") String section) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        // Lấy dữ liệu thống kê cho dashboard
        if ("dashboard".equals(section)) {
            long totalCustomers = customerService.getTotalCustomers();
            long totalProducts = productService.getTotalProducts();
            long totalOrders = orderService.getTotalOrders();
            int currentYear = LocalDate.now().getYear();
            int currentMonth = LocalDate.now().getMonthValue();
            double revenueThisMonth = orderService.getRevenueByMonth(currentYear, currentMonth);
            double revenueThisYear = orderService.getRevenueByYear(currentYear);

            model.addAttribute("totalCustomers", totalCustomers);
            model.addAttribute("totalProducts", totalProducts);
            model.addAttribute("totalOrders", totalOrders);
            model.addAttribute("revenueThisMonth", revenueThisMonth);
            model.addAttribute("revenueThisYear", revenueThisYear);
            model.addAttribute("currentYear", currentYear);
            model.addAttribute("currentMonth", currentMonth);
        }

        model.addAttribute("section", section);
        return "admin";
    }
}