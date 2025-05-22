    package com.data.ss11.controller.bt1;

    import com.data.ss11.model.bt1.User;

    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;

    import javax.validation.Valid;


    @Controller
    public class UserController {

        @GetMapping("/form")
        public String showForm(Model model) {
            model.addAttribute("user", new User());
            return "bt1/form";
        }

        @PostMapping("/submit")
        public String submitForm( @Valid @ModelAttribute("user")  User user,
                                 BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "bt1/form";
            }
                model.addAttribute("user", user);
            return "bt1/result";
        }
    }
