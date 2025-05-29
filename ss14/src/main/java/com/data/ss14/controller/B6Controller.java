package com.data.ss14.controller;
import com.data.ss14.model.B6.User;
import com.data.ss14.service.bt6.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class B6Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "/B6/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "password.mismatch");
        }

        if (result.hasErrors()) {
            return "B6/register";
        }

        userService.save(user);
        model.addAttribute("message", "success.message");
        return "/B6/result";
    }
}