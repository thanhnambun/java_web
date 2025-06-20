package ra.edu.project.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.project.dto.RecruitmentPositionDTO;
import ra.edu.project.entity.application.Application;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.recruitmentPosition.RecruitmentPosition;
import ra.edu.project.entity.user.User;
import ra.edu.project.service.*;

import java.io.IOException;

@Controller
@RequestMapping("user/recruitmentDetail")
public class RecruitmentDetailController {
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private RecruitmentPositionService recruitmentPositionService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private UserService userService;
    @GetMapping
    public String listRecruitment(){
        return "user/recruitmentDetail";
    }
    @PostMapping("/apply")
    public String applyForPosition(@RequestParam("recruitmentId") int recruitmentId,
                                   @CookieValue("username") String username,
                                   @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                                   Model model) {
        // Load lại dữ liệu position để còn hiển thị khi lỗi
        RecruitmentPositionDTO dto = recruitmentPositionService.findById(recruitmentId);
        model.addAttribute("position", dto);
        model.addAttribute("positions", recruitmentPositionService.findAllActive(0, 6)); // load danh sách liên quan

        try {
            User user = userService.getUserByUsername(username);
            Candidate candidate = candidateService.getCandidateByUserid(user.getId());

            if (candidate == null) {
                model.addAttribute("errorMessage", "Không tìm thấy thông tin ứng viên.");
                return "user/recruitmentDetail";
            }

            RecruitmentPosition recruitmentPosition = recruitmentPositionService.findByIdRecruitmentPosition(recruitmentId);
            if (recruitmentPosition == null) {
                model.addAttribute("errorMessage", "Vị trí tuyển dụng không tồn tại.");
                return "user/recruitmentDetail";
            }

            Application application = new Application();
            application.setCandidate(candidate);
            application.setRecruitmentPosition(recruitmentPosition);

            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = cloudinaryService.upload(imageFile, "user");
                application.setCvUrl(imageUrl);
            } else {
                model.addAttribute("errorMessage", "Vui lòng chọn file CV.");
                return "user/recruitmentDetail";
            }

            applicationService.applyNewApplication(application);
            model.addAttribute("successMessage", "Ứng tuyển thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Lỗi khi ứng tuyển: " + e.getMessage());
        }

        return "user/recruitmentDetail";
    }



}
