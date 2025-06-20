package ra.edu.project.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.project.dto.RecruitmentPositionDTO;
import ra.edu.project.service.RecruitmentPositionService;

import java.util.List;

@Controller
@RequestMapping("/user/home")
public class HomeUser {

    @Autowired
    private RecruitmentPositionService recruitmentPositionService;

    @GetMapping
    public String homeUser(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "6") int size) {
        List<RecruitmentPositionDTO> recruitmentPositionDTOS = recruitmentPositionService.findAllActive(page, size);
        model.addAttribute("positions", recruitmentPositionDTOS);
        return "user/home";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable("id") int id,
                             Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "6") int size) {
        RecruitmentPositionDTO dto = recruitmentPositionService.findById(id);
        List<RecruitmentPositionDTO> recruitmentPositionDTOS = recruitmentPositionService.findAllActive(page, size);
        model.addAttribute("positions", recruitmentPositionDTOS);
        model.addAttribute("position", dto);
        return "/user/recruitmentDetail";
    }

}
