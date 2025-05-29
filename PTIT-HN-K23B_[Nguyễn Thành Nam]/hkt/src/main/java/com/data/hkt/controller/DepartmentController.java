package com.data.hkt.controller;

import com.data.hkt.model.DepartmentModel;
import com.data.hkt.repository.department.DepartmentImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentImp service = new DepartmentImp();

    @GetMapping
    public String listDepartments(Model model) {
        List<DepartmentModel> departments = service.getDepartments(1, 100);
        model.addAttribute("departments", departments);
        model.addAttribute("department", new DepartmentModel());
        return "departments";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        DepartmentModel dept = service.getDepartmentById(id);
        model.addAttribute("department", dept);
        return "department_edit";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        service.deleteDepartment(id);
        return "redirect:/departments";
    }
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("department", new DepartmentModel());
        return "department_add";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute("department") @Valid DepartmentModel department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", service.getDepartments(1, 100));
            return "departments";
        }
        if (department.getDepartmentId() == 0) {
            service.addDepartment(department);
        } else {
            service.updateDepartment(department);
        }
        return "redirect:/departments";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("departments", service.getDepartmentByName(name));
        model.addAttribute("department", new DepartmentModel());
        return "departments";
    }
}
