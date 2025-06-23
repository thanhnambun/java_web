package ra.edu.project.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.service.CandidateService;
import ra.edu.project.service.UserService;

@Controller
@RequestMapping("/user/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/verify")
    public String verifyUser(@RequestParam String email,
                             @RequestParam String phone,
                             Model model) {
        Candidate candidate = candidateService.findByEmailAndPhone(email, phone);
        if (candidate != null) {
            model.addAttribute("userId", candidate.getUser().getId());
            return "reset-password";
        } else {
            model.addAttribute("error", "Email hoặc Số điện thoại không đúng.");
            return "forgot-password";
        }
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestParam int userId,
                                @RequestParam String newPassword,
                                Model model) {
        boolean result = userService.updatePassword(userId, newPassword);
        if (result) {
            model.addAttribute("message", "Đổi mật khẩu thành công. Vui lòng đăng nhập.");
            return "login";
        } else {
            model.addAttribute("error", "Đổi mật khẩu thất bại.");
            return "reset-password";
        }
    }
}

