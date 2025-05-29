package com.data.ss15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class bt1 {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "bt1";
    }
}
