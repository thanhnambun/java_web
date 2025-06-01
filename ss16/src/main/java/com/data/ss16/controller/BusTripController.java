package com.data.ss16.controller;

import com.data.ss16.model.BusTrip;
import com.data.ss16.model.User;
import com.data.ss16.service.BusService;
import com.data.ss16.service.BusTripService;
import com.data.ss16.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin/bustrip")
public class BusTripController {
    @Autowired
    private BusTripService busTripService;
    @Autowired
    private BusService busService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String list(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("busTrips", busTripService.findAll());
        return "bustrip-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("bustrip", new BusTrip());
        model.addAttribute("buses", busService.findAll());
        return "bustrip-form";
    }

    @PostMapping("/add")
    public String addBusTrip(@ModelAttribute("bustrip") @Valid BusTrip trip,
                             BindingResult result,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("buses", busService.findAll());
            return "bustrip-form";
        }
        if (!imageFile.isEmpty()) {
            String url = cloudinaryService.upload(imageFile, "bustrips");
            trip.setImage(url);
        }
        busTripService.addBusTrip(trip);
        return "redirect:/admin/bustrip";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        BusTrip trip = busTripService.findById(id);
        model.addAttribute("bustrip", trip);
        model.addAttribute("buses", busService.findAll());
        return "bustrip-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,
                       @ModelAttribute("bustrip") @Valid BusTrip trip,
                       BindingResult result,
                       @RequestParam("imageFile") MultipartFile imageFile,
                       Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("buses", busService.findAll());
            return "bustrip-form";
        }
        trip.setId(id);

        BusTrip oldTrip = busTripService.findById(id);
        if (!imageFile.isEmpty()) {
            String url = cloudinaryService.upload(imageFile, "bustrips");
            trip.setImage(url);
        } else if (oldTrip != null) {
            trip.setImage(oldTrip.getImage());
        }

        busTripService.updateBusTrip(trip);
        return "redirect:/admin/bustrip";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        busTripService.deleteBusTrip(id);
        return "redirect:/admin/bustrip";
    }
}