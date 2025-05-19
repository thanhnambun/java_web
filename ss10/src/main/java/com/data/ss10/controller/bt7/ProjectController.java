package com.data.ss10.controller.bt7;

import com.data.ss10.model.bt7.Document;
import com.data.ss10.model.bt7.Project;
import com.data.ss10.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final List<Project> projectList = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/new")
    public String showProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "/bt7/projectForm";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute("project") Project project,
                                @RequestParam("files") List<MultipartFile> files,
                                Model model) {
        try {
            List<Document> documentList = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String url = cloudinaryService.uploadFile(file);
                    documentList.add(new Document(file.getOriginalFilename(), url));
                }
            }
            project.setId(idGenerator.getAndIncrement());
            project.setDocuments(documentList);
            projectList.add(project);

            model.addAttribute("project", project);
            return "/bt7/projectResult";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Upload thất bại: " + e.getMessage());
            return "/bt7/projectForm";
        }
    }

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectList);
        return "/bt7/projectList";
    }
}