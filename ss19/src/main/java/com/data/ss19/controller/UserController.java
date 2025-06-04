package com.data.ss19.controller;

import com.data.ss19.model.User;
import com.data.ss19.repository.UserRepository;
import com.data.ss19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String listUsers(HttpSession session,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "5") int size,
                            @RequestParam(required = false) String search,
                            Model model) {


        List<User> users = userService.findAllWithPagination(page, size, search);
        long total = userService.countWithSearch(search);
        int totalPages = (int) Math.ceil((double) total / size);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);
        model.addAttribute("content", "admin/users");
        return "user-list";
    }

    @PostMapping("/toggle-status/{id}")
    public String toggleUserStatus(@PathVariable Integer id, HttpSession session) {
        User user = userService.findById(id);
        if (user != null) {
            user.setStatus(user.getStatus() == 1 ? 0 : 1);
            userService.update(user);
        }
        return "redirect:/users";
    }
}