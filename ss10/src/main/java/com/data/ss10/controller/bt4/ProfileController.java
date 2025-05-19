package com.data.ss10.controller.bt4;

import com.data.ss10.model.UserProfile;
import com.data.ss10.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/upload")
    public String showForm(Model model) {
        model.addAttribute("profile", new UserProfile());
        return "/bt4/uploadForm";
    }

    @PostMapping("/uploads")
    public String uploadAvatar(@ModelAttribute("profile") UserProfile profile, Model model) {
        try {
            String imageUrl = cloudinaryService.uploadFile(profile.getAvatar());
            model.addAttribute("username", profile.getUsername());
            model.addAttribute("avatarUrl", imageUrl);
            return "/bt4/result";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Upload thất bại: " + e.getMessage());
            return "/bt4/uploadForm";
        }
    }
}