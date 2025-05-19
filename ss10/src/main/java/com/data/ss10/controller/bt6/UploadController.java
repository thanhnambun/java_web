package com.data.ss10.controller.bt6;
import com.data.ss10.model.UploadFile;
import com.data.ss10.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/uploadBt6")
    public String showForm(Model model) {
        model.addAttribute("uploadFile", new UploadFile());
        return "/bt6/uploadForm";
    }

    @PostMapping("/uploadBt6")
    public String uploadFile(@ModelAttribute("uploadFile") UploadFile uploadFile, Model model) {
        try {
            String url = cloudinaryService.uploadFile(uploadFile.getFile());

            model.addAttribute("description", uploadFile.getDescription());
            model.addAttribute("fileUrl", url);

            return "/bt6/uploadResult";
        } catch (Exception e) {
            model.addAttribute("error", "Upload thất bại: " + e.getMessage());
            return "/bt6/uploadForm";
        }
    }
}