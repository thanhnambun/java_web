package ra.edu.project.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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
                                    @RequestParam("reason") String reason) {
        applicationService.cancelApplication(applicationId, reason);
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
                                      @RequestParam("interviewRequestDate") String dateStr,
                                      @RequestParam("interviewLink") String link,
                                      @RequestParam("interviewTime") String timeStr) {

        // Định dạng input type="date" và "datetime-local" từ HTML
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // cho input date
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); // cho datetime-local

        LocalDateTime interviewRequestDate = null;
        LocalDateTime interviewTime = null;

        try {
            // Convert interviewRequestDate (type="date" -> LocalDateTime)
            if (StringUtils.hasText(dateStr)) {
                interviewRequestDate = LocalDate.parse(dateStr, dateFormatter).atStartOfDay();
            }

            // Convert interviewTime (type="datetime-local" -> LocalDateTime)
            if (StringUtils.hasText(timeStr)) {
                interviewTime = LocalDateTime.parse(timeStr, datetimeFormatter);
            }

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            // Nếu có lỗi parse, redirect về trang error hoặc log để fix
            return "redirect:/error";
        }

        // Truyền đúng LocalDateTime sang Service (chuẩn theo kiểu Entity)
        applicationService.updateInterviewInfo(id, interviewRequestDate, link, interviewTime);

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

