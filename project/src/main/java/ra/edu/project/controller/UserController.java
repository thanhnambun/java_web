package ra.edu.project.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.edu.project.dto.RegistrationDTO;
import ra.edu.project.dto.UserDTO;
import ra.edu.project.dto.CandidateDTO;
import ra.edu.project.entity.user.User;
import ra.edu.project.entity.user.UserRole;
import ra.edu.project.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home( HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String role = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("role".equals(cookie.getName())) {
                    role = cookie.getValue();
                    break;
                }
            }
        }

        if ("ADMIN".equals(role)) {
            return "redirect:/admin/home";
        } else if ("USER".equals(role)) {
            return "redirect:/user/home";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "remember", required = false) String remember,
                        HttpServletResponse response,
                        Model model) {

        boolean rememberMe = "true".equals(remember);
        User user = userService.login(username, password, response, rememberMe);

        if (user != null) {
            if ("ADMIN".equals(user.getRole().toString())) {
                return "redirect:/admin/home";
            } else if ("USER".equals(user.getRole().toString())) {
                return "redirect:/user/home";
            }
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }



    @GetMapping("/register")
    public String showRegister(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setUserDTO(new UserDTO());
        registrationDTO.setCandidateDTO(new CandidateDTO());
        model.addAttribute("registrationDTO", registrationDTO);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegistrationDTO registrationDTO, Model model) {
        System.out.println(registrationDTO);
        List<String> errors = userService.register(registrationDTO);
        if (errors == null) {
            return "redirect:/login";
        }
        model.addAttribute("errors", errors);
        return "register";
    }
}