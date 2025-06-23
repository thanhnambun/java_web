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
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.user.User;
import ra.edu.project.entity.user.UserRole;
import ra.edu.project.service.CandidateService;
import ra.edu.project.service.CandidateTechnologyService;
import ra.edu.project.service.TechnologyService;
import ra.edu.project.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private CandidateTechnologyService candidateTechnologyService;
    @Autowired
    private CandidateService candidateService;


    @GetMapping("/")
    public String home(HttpServletRequest request) {
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
    public String login(@ModelAttribute("userDTO") UserDTO userDTO,
                        @RequestParam(value = "remember", required = false) String remember,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {

        boolean rememberMe = "true".equals(remember);

        User user = userService.login(userDTO.getUsername(), userDTO.getPassword(), request, response, rememberMe);

        if (user != null) {
            if (user.getRole().equals(UserRole.ADMIN)) {
                return "redirect:/admin/home";
            } else if (user.getRole().equals(UserRole.CANDIDATE)) {
                return "redirect:/user/home";
            }
        }


        model.addAttribute("error", "Invalid username or password");
        model.addAttribute("userDTO", userDTO);
        return "login";
    }



    @GetMapping("/register")
    public String showRegister(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setUserDTO(new UserDTO());
        registrationDTO.setCandidateDTO(new CandidateDTO());
        List<String> technologyNames = technologyService.getAllTechnologyNames();
        model.addAttribute("technologyList", technologyNames);
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
        List<String> technologyNames = technologyService.getAllTechnologyNames();
        model.addAttribute("technologyList", technologyNames);
        model.addAttribute("errors", errors);
        return "register";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);
        return "redirect:/login";
    }

}