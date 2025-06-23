package ra.edu.project.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.project.dto.ApplicationDTO;
import ra.edu.project.entity.application.Application;
import ra.edu.project.entity.application.Progress;
import ra.edu.project.service.ApplicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/application")
public class AdminApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public String listApplications(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) Progress progress,
            @RequestParam(required = false) String result,
            @RequestParam(required = false) String keyword,
            Model model) {

        // Sửa lỗi NPE
        String progressValue = (progress != null) ? progress.name() : null;

        List<Application> applications = applicationService.searchApplications(progressValue, result, keyword, page, size);
        List<ApplicationDTO> applicationDTOS = new ArrayList<>();
        for (Application application : applications) {
            applicationDTOS.add(applicationService.convertToDTO(application));
        }
        int totalItems = applicationService.countSearchApplications(progressValue, result, keyword);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        if (applicationDTOS == null || applications.isEmpty()) {
            model.addAttribute("message", "Không có đơn ứng tuyển nào.");
        } else {
            model.addAttribute("applications", applicationDTOS);
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("progress", progress);
        model.addAttribute("result", result);
        model.addAttribute("keyword", keyword);

        return "admin/application";
    }


    @PostMapping("/update-progress-to-handling/{id}")
    @ResponseBody
    public String updateProgressToHandlingAjax(@PathVariable("id") int id) {
        Application app = applicationService.findById(id);
        if (app != null && app.getProgress() == Progress.PENDING) {
            applicationService.updateProgressToHandling(id);
            return "Updated to HANDLING";
        }
        return "No update";
    }

    @PostMapping("/cancel")
    public String cancelApplication(@RequestParam("applicationId") int applicationId,
                                    @RequestParam("reason") String reason,
                                    RedirectAttributes redirectAttributes) {
            Application application = applicationService.findById(applicationId);
            if (application.getProgress() == Progress.DONE) {
                redirectAttributes.addFlashAttribute("message", "Không thể hủy đơn khi đơn đã hoàn thành.");
            }
            applicationService.cancelApplication(applicationId, reason);
            redirectAttributes.addFlashAttribute("message", "Hủy đơn thành công.");
        return "redirect:/admin/application";
    }


    @GetMapping("/detail/{id}")
    @ResponseBody
    public ApplicationDTO getApplicationDetail(@PathVariable("id") int id) {
        Application application = applicationService.findById(id);
        return applicationService.convertToDTO(application);
    }

    @PostMapping("/interview")
    public String updateInterviewInfo(@RequestParam("applicationId") int id,
                                      @RequestParam("interviewDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime interviewDateTime,
                                      @RequestParam("interviewLink") String link) {
        applicationService.updateInterviewInfo(id, interviewDateTime, link, interviewDateTime);
        return "redirect:/admin/application";
    }



    @PostMapping("/result")
    public String updateInterviewResult(@RequestParam("applicationId") int id,
                                        @RequestParam("note") String note,
                                        @RequestParam("result") String result) {
            applicationService.updateInterviewResult(id, note, result);
        return "redirect:/admin/application";
    }
}

