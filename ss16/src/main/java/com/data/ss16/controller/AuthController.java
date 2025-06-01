package com.data.ss16.controller;


import com.data.ss16.model.User;
import com.data.ss16.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user,
                           BindingResult result,
                           Model model) {
        StringBuilder errorMsg = new StringBuilder();
        if (!result.hasErrors()) {
            if (!userService.register(user, errorMsg)) {
                result.rejectValue("username", "error.user", errorMsg.toString());
            }
        }
        if (result.hasErrors()) {
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,
                        Model model,
                        HttpSession session) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser == null) {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu hoặc tài khoản bị khóa");
            return "login";
        }
        session.setAttribute("currentUser", loginUser);
        if ("ADMIN".equals(loginUser.getRole())) return "redirect:/admin";
        else return "redirect:/bus/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}