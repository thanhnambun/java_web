package ra.edu.project.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ra.edu.project.dto.RegistrationDTO;
import ra.edu.project.dto.UserDTO;
import ra.edu.project.dto.CandidateDTO;
import ra.edu.project.entity.user.User;
import ra.edu.project.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO, Model model, HttpSession session, HttpServletResponse response) {

        User user = userService.login(userDTO.getUsername(), userDTO.getPassword(), response);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/home";
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
//        String password = registrationDTO.getUserDTO().getPassword();
//        String password2 = BCrypt.hashpw(password, BCrypt.gensalt(10));
        System.out.println(registrationDTO);
        List<String> errors = userService.register(registrationDTO);
        if (errors == null) {
            return "redirect:/login";
        }
        model.addAttribute("errors", errors);
        return "register";
    }
}