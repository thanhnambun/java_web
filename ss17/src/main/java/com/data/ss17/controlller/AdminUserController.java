package com.data.ss17.controlller;

import com.data.ss17.model.Customer;
import com.data.ss17.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
    @Autowired
    private CustomerRepository customerService;

    private boolean checkAdminAccess(HttpSession session) {
        Customer user = (Customer) session.getAttribute("currentUser");
        return user != null && "ADMIN".equals(user.getRole());
    }

    @GetMapping
    public String listUsers(HttpSession session,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false) String search,
                            Model model) {
        if (!checkAdminAccess(session)) return "redirect:/login";
        if (search != null && (search.trim().isEmpty() || "null".equals(search))) {
            search = null;
        }
        List<Customer> users = customerService.findAllWithPagination(page, size, search);
        long total = customerService.countWithSearch(search);
        int totalPages = (int) Math.ceil((double) total / size);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);
        model.addAttribute("content", "admin/users");
        return "admin/admin-layout";
    }

    @PostMapping("/toggle-status/{id}")
    public String toggleUserStatus(@PathVariable Long id, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/login";
        Customer user = customerService.findById(id);
        if (user != null) {
            user.setStatus(user.getStatus() == 1 ? 0 : 1);
            customerService.update(user);
        }
        return "redirect:/admin/users";
    }
}