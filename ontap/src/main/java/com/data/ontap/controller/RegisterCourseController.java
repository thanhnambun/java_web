package com.data.ontap.controller;

import com.data.ontap.dto.RegisterCourseDTO;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RegisterService registerService;
    @GetMapping("/register-course-form")
    public String showRegisterForm(Model model,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int pageSize) {
        model.addAttribute("courses", courseService.findAllCous());
        model.addAttribute("students", studentService.findAllSt());

        List<RegisterCourse> courses = registerService.findAll(page, pageSize);
        List<RegisterCourseDTO> registerCourseDTOList = new ArrayList<>();

        for (RegisterCourse rc : courses) {
            RegisterCourseDTO dto = new RegisterCourseDTO();
            dto.setId(rc.getId());
            dto.setCourseName(rc.getCourse().getName());
            dto.setStudentName(rc.getStudent().getName());
            registerCourseDTOList.add(dto);
        }

        model.addAttribute("registers", registerCourseDTOList);

        return "register-course";
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
