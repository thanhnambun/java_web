package com.data.ss16.controller;

import com.data.ss16.model.Bus;
import com.data.ss16.model.User;
import com.data.ss16.service.BusService;
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
@RequestMapping("/admin/bus")
public class BusController {
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
        model.addAttribute("buses", busService.findAll());
        return "bus-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "bus-form";
    }

    @PostMapping("/add")
    public String addBus(@ModelAttribute("bus") @Valid Bus bus,
                         BindingResult result,
                         @RequestParam("imageFile") MultipartFile imageFile,
                         Model model) throws IOException {
        if (result.hasErrors()) return "bus-form";
        if (!imageFile.isEmpty()) {
            String url = cloudinaryService.upload(imageFile, "buses");
            bus.setImage(url);
        }
        bus.setTotalSeat(bus.getRowSeat() * bus.getColSeat());
        busService.addBus(bus);
        return "redirect:/admin/bus";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Bus bus = busService.findById(id);
        model.addAttribute("bus", bus);
        return "bus-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,
                       @ModelAttribute("bus") @Valid Bus bus,
                       BindingResult result,
                       @RequestParam("imageFile") MultipartFile imageFile,
                       Model model) throws IOException {
        if (result.hasErrors()) return "bus-form";
        bus.setId(id);
        bus.setTotalSeat(bus.getRowSeat() * bus.getColSeat());

        Bus oldBus = busService.findById(id);
        if (!imageFile.isEmpty()) {
            String url = cloudinaryService.upload(imageFile, "buses");
            bus.setImage(url);
        } else if (oldBus != null) {
            bus.setImage(oldBus.getImage());
        }
        busService.updateBus(bus);
        return "redirect:/admin/bus";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        busService.deleteBus(id);
        return "redirect:/admin/bus";
    }
}