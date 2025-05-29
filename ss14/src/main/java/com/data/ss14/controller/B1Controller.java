package com.data.ss14.controller;

import com.data.ss14.model.B1.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@RequestMapping("B1")
@Controller
public class B1Controller {
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "B1/login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") User user, Model model, HttpSession session) {
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            session.setAttribute("loggedInUser", user.getUsername());
            return "redirect:/B1/welcome";
        } else {
            model.addAttribute("error", "Tên người dùng hoặc mật khẩu không đúng!");
            return "B1/login";
        }
    }

    @GetMapping("/welcome")
    public String showWelcomePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("loggedInUser");
        if (username != null) {
            model.addAttribute("username", username);
            return "B1/welcome";
        } else {
            return "redirect:/login";
        }
    }


}