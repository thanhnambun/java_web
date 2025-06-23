package ra.edu.project.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.project.dto.ApplicationDTO;
import ra.edu.project.entity.application.Application;
import ra.edu.project.entity.application.Progress;
import ra.edu.project.entity.application.RequestResult;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.user.User;
import ra.edu.project.service.ApplicationService;
import ra.edu.project.service.CandidateService;
import ra.edu.project.service.TechnologyService;
import ra.edu.project.service.UserService;

import javax.servlet.http.Cookie;
import java.util.List;

@Controller
@RequestMapping("user/myApplication")
public class MyApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserService userService;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private CandidateService candidateService;
    private static final int PAGE_SIZE = 10;

    @GetMapping("")
    public String myApplication(Model model,
                                @CookieValue(value = "username", required = false) String username,
                                @RequestParam(defaultValue = "1") int page) {
        if (username == null) {
            return "redirect:/login";
        }

        User user = userService.getUserByUsername(username);
        Candidate candidate = candidateService.getCandidateByUserid(user.getId());

        List<ApplicationDTO> applicationList = applicationService.getApplicationsByCandidate(candidate.getId(), page, PAGE_SIZE);

        int totalItems = applicationService.countApplicationsByCandidate(candidate.getId());

        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);


        model.addAttribute("applicationList", applicationList);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);

        return "user/myApplication";
    }


    @PostMapping("/{applicationId}/confirm")
    public String confirmInterview(@PathVariable("applicationId") int applicationId,
                                   @RequestParam("result") String result,
                                   @CookieValue("username") String username,
                                   Model model) {

        User user = userService.getUserByUsername(username);
        int candidateId = candidateService.getCandidateByUserid(user.getId()).getId();

        ApplicationDTO applicationDTO = applicationService.getApplicationDetailForCandidate(candidateId, applicationId);

        if (applicationDTO == null) {
            model.addAttribute("errorMessage", "Không tìm thấy đơn ứng tuyển.");
            return "user/myApplication";
        }

        try {
            if ("ACCEPT".equalsIgnoreCase(result)) {
                applicationService.updateCandidateInterviewResponse(applicationId, RequestResult.ACCEPT);
                model.addAttribute("successMessage", "Xác nhận tham gia phỏng vấn thành công.");
            } else if ("REJECT".equalsIgnoreCase(result)) {
                applicationService.updateCandidateInterviewResponse(applicationId, RequestResult.DECLINED);
                applicationService.updateProgress(applicationId, Progress.DONE.name());
                model.addAttribute("successMessage", "Từ chối phỏng vấn thành công.");
            } else {
                model.addAttribute("errorMessage", "Giá trị xác nhận không hợp lệ.");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi cập nhật xác nhận: " + e.getMessage());
        }
        List<ApplicationDTO> applicationList = applicationService.getApplicationsByCandidate(candidateId, 1, 6);
        model.addAttribute("applicationList", applicationList);
        return "user/myApplication";
    }

    @GetMapping("/detail/{applicationId}")
    public String viewApplicationDetail(@PathVariable int applicationId,
                                        @CookieValue("username") String username,
                                        Model model) {
        User user = userService.getUserByUsername(username);
        int candidateId = candidateService.getCandidateByUserid(user.getId()).getId();
        ApplicationDTO applicationDTO = applicationService.getApplicationDetailForCandidate(candidateId, applicationId);

        model.addAttribute("applicationDTO", applicationDTO);
        return "user/myApplication";
    }
    @PostMapping("/cancel")
    public String cancelApplication(@RequestParam("applicationId") int applicationId,
                                    @RequestParam("reason") String reason,
                                    RedirectAttributes redirectAttributes) {
        Application application = applicationService.findById(applicationId);
        if (application.getProgress() == Progress.HANDLING) {
            redirectAttributes.addFlashAttribute("message", "Không thể hủy đơn khi đơn đang được xem .");
        }
        applicationService.cancelApplication(applicationId, reason);
        redirectAttributes.addFlashAttribute("message", "Hủy đơn thành công.");
        return "redirect:/admin/application";
    }
}
