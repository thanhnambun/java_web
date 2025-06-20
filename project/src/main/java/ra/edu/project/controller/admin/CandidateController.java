package ra.edu.project.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.project.dto.CandidateDTO;
import ra.edu.project.entity.user.Status;
import ra.edu.project.service.CandidateService;
import ra.edu.project.service.TechnologyService;

import java.util.List;

@Controller
@RequestMapping("/admin/candidates")
public class CandidateController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private TechnologyService technologyService;

    @GetMapping
    public String getAllCandidates(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer experience,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String technology,
            Model model) {

        // Gọi service filter tổng hợp
        List<CandidateDTO> candidates = candidateService.filterCandidates(
                keyword, experience, age, gender, technology, page, PAGE_SIZE
        );

        // Lấy tổng số bản ghi sau filter
        int totalItems = candidateService.countFilteredCandidates(
                keyword, experience, age, gender, technology
        );

        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        // Truyền dữ liệu cho view
        model.addAttribute("candidates", candidates);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("experience", experience);
        model.addAttribute("age", age);
        model.addAttribute("gender", gender);
        model.addAttribute("technology", technology);

        List<String> technologyNames = technologyService.getAllTechnologyNames();
        model.addAttribute("technologyList", technologyNames);

        return "admin/candidate";
    }

    @PostMapping("/change-status")
    public String changeCandidateStatus(@RequestParam int userId,
                                        @RequestParam String newStatus) {
        Status status = Status.valueOf(newStatus.toUpperCase());
        candidateService.changeStatus(userId, status);
        return "redirect:/admin/candidates";
    }
}
