package com.data.ss18_2.controller;

import com.data.ss18_2.model.User;
import com.data.ss18_2.repository.UserRepository;
import com.data.ss18_2.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("listUser")
    public String listUser(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "listUser";
    }
    @GetMapping("add")
    public String addUser(@Valid User user, Model model) {

    }
}
