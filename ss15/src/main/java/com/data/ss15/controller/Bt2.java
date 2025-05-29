package com.data.ss15.controller;

import com.data.ss15.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class Bt2 {
    @GetMapping("StudentList")
    public String StudentList(Model model) {
        List<Student> students = Arrays.asList(
                new Student("SV001", "Nguyen Van A", 20, "DHTH1", "a@gmail.com", "Ha Noi", "0909123456"),
                new Student("SV002", "Tran Thi B", 21, "DHTH2", "b@gmail.com", "Da Nang", "0912345678"),
                new Student("SV003", "Le Van C", 22, "DHTH1", "c@gmail.com", "HCM", "0987654321")
        );
        model.addAttribute("students", students);
        return "bt2";
    }
}
