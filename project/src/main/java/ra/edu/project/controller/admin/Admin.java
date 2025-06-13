package ra.edu.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Admin {
    @GetMapping("/home")
    public String home(Model model) {
        return "admin/home";
    }
    @GetMapping("/sidebar")
    public String sidebar(Model model) {
        return "admin/sidebar";
    }
}
