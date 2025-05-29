
package com.data.ss15.controller.bt10;

import com.data.ss15.model.Resume;
import com.data.ss15.service.ResumeService;
import com.data.ss15.service.ResumeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/resumes")
public class ResumeController {
    private ResumeService resumeService = new ResumeServiceImpl();

    @GetMapping
    public String listResumes(Model model) {
        model.addAttribute("resumes", resumeService.getAllResumes());
        return "/bt10/list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("resume", new Resume());
        return "/bt10/form";
    }

    @PostMapping("/add")
    public String addResume(@ModelAttribute("resume") @Valid Resume resume,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/bt10/form";
        }
        resumeService.addResume(resume);
        return "redirect:/resumes";
    }

    @GetMapping("/delete/{id}")
    public String deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return "redirect:/resumes";
    }
}
