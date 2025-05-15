package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute User user, Model model) {
        Map<String, String> errors = new HashMap<>();

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            errors.put("nameError", "Tên không được để trống");
        }

        if (user.getEmail() == null || !user.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.put("emailError", "Email không hợp lệ");
        }

        if (user.getPhone() == null || !user.getPhone().matches("^\\d{10}$")) {
            errors.put("phoneError", "Số điện thoại phải gồm 10 chữ số");
        }

        if (!errors.isEmpty()) {
            model.addAllAttributes(errors);
            return "registration";
        }

        model.addAttribute("user", user);
        return "result";
    }
}
