package com.data.sss18_3.controller;

import com.data.sss18_3.UserDTO;
import com.data.sss18_3.model.User;
import com.data.sss18_3.repository.UserRepository;
import com.data.sss18_3.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/listUser")
    public String listUser(@RequestParam(value = "search", required = false) String search, Model model) {
        List<User> users;
        if (search != null && !search.isEmpty()) {
            users = userRepository.findByNameOrEmail(search);
            if (users == null || users.isEmpty()) {
                model.addAttribute("notFoundMessage", "Không tìm thấy người dùng phù hợp.");
            }
        } else {
            users = userRepository.findAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("search", search);
        return "listUser";
    }


    @GetMapping("add")
    public String addUser( Model model) {
        model.addAttribute("user", new UserDTO());
        return "addUser";
    }
    @PostMapping("/add")
    public String addUserSubmit(@Valid @ModelAttribute("user") User user,
                                BindingResult bindingResult,
                                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addUser";
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = cloudinaryService.upload(imageFile, "user");
            user.setImg(imageUrl);
        }

        userRepository.save(user);
        return "redirect:/listUser";
    }


    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userRepository.findById(id);
        if (user == null) {
            return "redirect:/listUser";
        }
        model.addAttribute("user", user);
        return "editUser";
    }
    @PostMapping("/edit")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             @RequestParam("imageFile") MultipartFile file,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "editUser";
        }
        try {
            if (!file.isEmpty()) {
                String imageUrl = cloudinaryService.upload(file,"user");
                user.setImg(imageUrl);
            }
        } catch (Exception e) {
            model.addAttribute("uploadError", "Lỗi khi upload ảnh: " + e.getMessage());
            return "editUser";
        }
        userRepository.update(user);
        return "redirect:/listUser";
    }
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userRepository.delete(id);
        return "redirect:/listUser";
    }

}
