package com.data.ss16.controller;

import com.data.ss16.model.User;
import com.data.ss16.service.BusTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bus")
public class BusUserController {
    @Autowired
    private BusTripService busTripService;

    @GetMapping("/list")
    public String listTrips(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) return "redirect:/login";
        model.addAttribute("busTrips", busTripService.findAll());
        return "bus-list-user";
    }
}