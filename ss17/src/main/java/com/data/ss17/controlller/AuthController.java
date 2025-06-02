package com.data.ss17.controlller;

import com.data.ss17.model.Customer;
import com.data.ss17.model.CustomerLoginDTO;
import com.data.ss17.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthController {
    private final CustomerRepository customerRepository;
    @Autowired
    public  AuthController(CustomerRepository  cusRepo) {
        this.customerRepository = cusRepo;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("customer") Customer customer,
                           BindingResult result,
                           Model model) {
        System.out.println(88);
        if (result.hasErrors()) {
            System.out.println(7);
            return "register";
        }
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {

            System.out.println(6);
            model.addAttribute("emailExistsError", "Email đã tồn tại");
            return "register";
        }

        // Set role và status mặc định
        customer.setRole("USER");
        customer.setStatus("ACTIVE");

        // Lưu Customer vào database
        customerRepository.save(customer);

        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new CustomerLoginDTO());
        return "login";
    }
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") CustomerLoginDTO loginDTO,
                        BindingResult result,
                        Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }

        Optional<Customer> optionalCustomer = customerRepository.findByUsername(loginDTO.getUsername());
        System.out.println(loginDTO.getUsername());
        if (!optionalCustomer.isPresent()) {
            model.addAttribute("error", "Tài khoản không tồn tại");
            return "login";
        }

        Customer customer = optionalCustomer.get();

        if (!customer.getPassword().equals(loginDTO.getPassword())) {
            model.addAttribute("error", "Sai mật khẩu");
            return "login";
        }
        session.setAttribute("currentUser", customer);
        // Kiểm tra role và chuyển hướng
        if ("ADMIN".equalsIgnoreCase(customer.getRole())) {
            return "redirect:/admin";
        } else if ("USER".equalsIgnoreCase(customer.getRole())) {
            return "redirect:/home";
        }

        model.addAttribute("error", "Role không hợp lệ");
        return "login";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
