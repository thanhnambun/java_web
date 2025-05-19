package com.data.ss10.controller.bt5;
import com.data.ss10.model.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class DocumentController {

    @GetMapping("/document/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("document", new Document());
        return "/bt5/documentForm";
    }

    @PostMapping("/document/upload")
    public String uploadDocument(@ModelAttribute("document") Document document, Model model) {
        MultipartFile file = document.getFile();

        if (file == null || file.isEmpty()) {
            model.addAttribute("error", "Vui lòng chọn file để upload!");
            return "/bt5/documentForm";
        }

        if (!file.getOriginalFilename().endsWith(".pdf")) {
            model.addAttribute("error", "Chỉ cho phép upload file PDF!");
            return "/bt5/documentForm";
        }

        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            model.addAttribute("message", "Upload tài liệu thành công!");
            model.addAttribute("document", document);
            model.addAttribute("filePath", filePath);

            return "/bt5/uploadResult";

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Upload thất bại: " + e.getMessage());
            return "/bt5/documentForm";
        }
    }
}