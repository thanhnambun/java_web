package com.data.ontap.controller;

import com.data.ontap.model.Student;
import com.data.ontap.repository.RegisterCourseRepo;
import com.data.ontap.service.CloudinaryService;
import com.data.ontap.service.CourseService;
import com.data.ontap.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private RegisterCourseRepo registerCourseRepo;

    @GetMapping("/list")
    public String listStudents(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model) {
        List<Student> students = studentService.findAll(page, size);
        model.addAttribute("students", students);
        model.addAttribute("currentPage", page);
        return "/student/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "/student/form";
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/student/form";
        }
        try {
            if (!imageFile.isEmpty()) {
                String imageUrl = cloudinaryService.upload(imageFile, "students");
                student.setAvatar(imageUrl);
            }
            studentService.save(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/student/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = studentService.findById(id);
        if (student == null) {
            return "redirect:/student/list";
        }
        model.addAttribute("student", student);
        return "/student/form";
    }

    @PostMapping("/edit")
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                @RequestParam("imageFile") MultipartFile file,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "/student/form";
        }

        try {
            if (!file.isEmpty()) {
                String imageUrl = cloudinaryService.upload(file, "students");
                student.setAvatar(imageUrl); // Set ảnh mới
            } else {
                Student existingStudent = studentService.findById(student.getId());
                if (existingStudent != null) {
                    student.setAvatar(existingStudent.getAvatar());
                }
            }
        } catch (Exception e) {
            model.addAttribute("uploadError", "Lỗi khi upload ảnh: " + e.getMessage());
            return "/student/form";
        }

        studentService.update(student);
        return "redirect:/student/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        registerCourseRepo.deleteStudent(id);
        studentService.delete(id);
        return "redirect:/student/list";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam String keyword,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {
        List<Student> students = studentService.findByName(keyword, page, size);
        model.addAttribute("students", students);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "/student/list";
    }
}
