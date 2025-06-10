package com.data.ontap.controller;

import com.data.ontap.model.RegisterCourse;
import com.data.ontap.service.CourseService;
import com.data.ontap.service.RegisterService;
import com.data.ontap.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RegisterCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RegisterService registerService;
    @GetMapping("/register-courses")
    public ResponseEntity<List<RegisterCourse>> getAllRegisterCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<RegisterCourse> courses = registerService.findAll(page, pageSize);

        return ResponseEntity.ok(courses);
    }
    @PostMapping("/register-course")
    public ResponseEntity<String> addRegisterCourse(@RequestParam Long idCourse,
                                                    @RequestParam String idStudent) {
        boolean result = registerService.addCourse(idCourse, idStudent);
        if (result) {
            return ResponseEntity.ok("Register course success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Register course failed");
        }
    }

}
