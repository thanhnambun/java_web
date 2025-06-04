package com.data.ss19.controller;

import com.data.ss19.model.Theater;
import com.data.ss19.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/theaters")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public String listTheaters(Model model) {
        List<Theater> theaters = theaterService.findAll();
        model.addAttribute("theaters", theaters);
        return "theaters/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("theater", new Theater());
        return "theaters/form";
    }

    @PostMapping("/add")
    public String addTheater(@ModelAttribute("theater") @Valid Theater theater,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "theaters/form";
        }
        theaterService.save(theater);
        return "redirect:/theaters";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Theater theater = theaterService.findById(id);
        if (theater == null) {
            return "redirect:/theaters";
        }
        model.addAttribute("theater", theater);
        return "theaters/form";
    }

    @PostMapping("/edit/{id}")
    public String editTheater(@PathVariable Long id,
                              @ModelAttribute("theater") @Valid Theater theater,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            return "theaters/form";
        }
        theater.setTheater_id(id);
        theaterService.update(theater);
        return "redirect:/theaters";
    }

    @GetMapping("/delete/{id}")
    public String deleteTheater(@PathVariable Long id) {
        theaterService.delete(id);
        return "redirect:/theaters";
    }
}