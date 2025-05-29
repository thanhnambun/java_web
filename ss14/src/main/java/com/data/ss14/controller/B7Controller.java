package com.data.ss14.controller;

import javax.servlet.http.*;

import com.data.ss14.model.B7.User;
import com.data.ss14.service.bt7.UserService;
import com.data.ss14.service.bt7.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class B7Controller {

    private UserService userService = new UserServiceImpl();

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpServletRequest request) {
        User user = new User();

        // Đọc cookie nếu có để tự động điền username/password
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) user.setUsername(c.getValue());
                if ("password".equals(c.getName())) user.setPassword(c.getValue());
            }
        }

        model.addAttribute("user", user);
        return "/B7/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,
                        @RequestParam(value = "remember", required = false) String remember,
                        HttpSession session,
                        HttpServletResponse response,
                        Model model) {
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());

        if (loggedInUser != null) {
            // Đăng nhập thành công: lưu session
            session.setAttribute("user", loggedInUser);

            // Nếu chọn ghi nhớ, tạo cookie
            if ("on".equals(remember)) {
                Cookie usernameCookie = new Cookie("username", loggedInUser.getUsername());
                Cookie passwordCookie = new Cookie("password", loggedInUser.getPassword());

                // Đặt cookie tồn tại 7 ngày
                usernameCookie.setMaxAge(7 * 24 * 60 * 60);
                passwordCookie.setMaxAge(7 * 24 * 60 * 60);

                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            } else {
                // Nếu không chọn ghi nhớ, xóa cookie cũ
                Cookie usernameCookie = new Cookie("username", null);
                Cookie passwordCookie = new Cookie("password", null);
                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }

            return "redirect:/B7/welcome";
        } else {
            model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return "/B7/login";
        }
    }

    @GetMapping("/welcome")
    public String welcome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("username", user.getUsername());
        return "/B7/welcome";
    }
}