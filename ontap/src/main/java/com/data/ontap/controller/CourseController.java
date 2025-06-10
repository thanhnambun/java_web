package com.data.ontap.controller;

import com.data.ontap.model.Course;
import com.data.ontap.service.CourseService;
import com.data.ontap.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/list")
    public String listCourses(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        model.addAttribute("courses", courseService.getAll(page, size));
        model.addAttribute("currentPage", page);
        return "course/list";
    }

    // Show create form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        return "course/form";
    }

    // Handle create
    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute Course course,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "course/form";
        }
        courseService.createCourse(course);
        return "redirect:/course/list";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        return "course/form";
    }

    // Handle update
    @PostMapping("/edit")
    public String updateCourse(@Valid @ModelAttribute Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "course/form";
        }
        courseService.updateCourse(course);
        return "redirect:/course/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        List<Course> courseList = registerService.findByIdCourse(id);
        if (courseList.isEmpty()) {
            courseService.deleteCourse(id);
            redirectAttributes.addFlashAttribute("success", "Xóa khóa học thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa: Khóa học đã có sinh viên đăng ký!");
        }
        return "redirect:/course/list";
    }


    // Search
    @GetMapping("/search")
    public String searchCourses(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        model.addAttribute("courses", courseService.searchByName(keyword, page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "course/list";
    }
}
