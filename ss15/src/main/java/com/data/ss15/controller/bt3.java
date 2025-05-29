package com.data.ss15.controller;

import com.data.ss15.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@RequestMapping("bt3")
@Controller
public class bt3 {
    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "bt3/form";
    }
    @PostMapping("form")
    public String post(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bt3/form";
        }
        return "redirect:/bt3/result";
    }
    @GetMapping("result")
    public String result() {
        return "bt3/result";
    }
}
