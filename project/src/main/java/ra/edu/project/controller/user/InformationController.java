package ra.edu.project.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.project.dto.CandidateDTO;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.user.User;
import ra.edu.project.repository.candidate.CandidateRepository;
import ra.edu.project.repository.user.UserRepository;
import ra.edu.project.service.CandidateService;
import ra.edu.project.service.InformationService;
import ra.edu.project.service.TechnologyService;
import ra.edu.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private CandidateService candidateService;

    // Hiển thị thông tin cá nhân
    @GetMapping
    public String information(Model model, HttpServletRequest request) {
        String username = userService.getCurrentUsername(request);
        if (username == null) {
            return "redirect:/login";
        }

        User user = userService.getUserByUsername(username);
        Candidate candidate = candidateService.getCandidateByUserid(user.getId());
        if (candidate == null) {
            model.addAttribute("errorMsg", "Không tìm thấy thông tin ứng viên!");
            return "user/information";
        }
        CandidateDTO candidateDTO = candidateService.convertToDTO(candidate);

        List<String> technologyNames = technologyService.getAllTechnologyNames();

        model.addAttribute("candidateDTO", candidateDTO);
        model.addAttribute("technologyList", technologyNames);

        return "user/information";
    }

    @PostMapping("/update-info")
    public String updateCandidateInfo(@ModelAttribute("candidateDTO") CandidateDTO candidateDTO,
                                      HttpServletRequest request,
                                      Model model) {
        List<String> errors = informationService.updateCandidateInfo(candidateDTO, request);

        String username = userService.getCurrentUsername(request);
        User user = userService.getUserByUsername(username);
        Candidate candidate = candidateService.getCandidateByUserid(user.getId());
        if (candidate == null) {
            model.addAttribute("errorMsg", "Không tìm thấy thông tin ứng viên!");
            return "user/information"; // hoặc trả về view lỗi nếu muốn
        }
        CandidateDTO updatedDTO = candidateService.convertToDTO(candidate);

        List<String> technologyNames = technologyService.getAllTechnologyNames();

        model.addAttribute("candidateDTO", updatedDTO);
        model.addAttribute("technologyList", technologyNames);

        if (errors == null || errors.isEmpty()) {
            model.addAttribute("successMsg", "Cập nhật thông tin thành công!");
        } else {
            model.addAttribute("errorMsgs", errors);
        }

        return "user/information";
    }


    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("phone") String phone,
                                 HttpServletRequest request,
                                 Model model) {

        String username = userService.getCurrentUsername(request);
        if (username == null) {
            model.addAttribute("errorMsg", "Chưa đăng nhập.");
            return "redirect:/login";
        }

        User user = userService.getUserByUsername(username);
        int result = informationService.changePassword(user.getId(), oldPassword, newPassword, phone);

        Candidate candidate = candidateService.getCandidateByUserid(user.getId());
        if (candidate == null) {
            model.addAttribute("errorMsg", "Không tìm thấy thông tin ứng viên!");
            return "user/information";
        }
        CandidateDTO candidateDTO = candidateService.convertToDTO(candidate);
        List<String> technologyNames = technologyService.getAllTechnologyNames();

        model.addAttribute("candidateDTO", candidateDTO);
        model.addAttribute("technologyList", technologyNames);

        switch (result) {
            case 1:
                model.addAttribute("successMsg", "Đổi mật khẩu thành công!");
                break;
            case -1:
                model.addAttribute("errorMsg", "Không tìm thấy người dùng!");
                break;
            case -2:
                model.addAttribute("errorMsg", "Không tìm thấy ứng viên!");
                break;
            case -3:
                model.addAttribute("errorMsg", "Mật khẩu cũ không đúng!");
                break;
            case -4:
                model.addAttribute("errorMsg", "Số điện thoại không chính xác!");
                break;
            default:
                model.addAttribute("errorMsg", "Đã xảy ra lỗi không xác định.");
        }

        return "user/information";
    }
}
