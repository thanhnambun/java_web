package ra.edu.project.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user/listRecruitment")
public class ListRecruitmentController {
    @GetMapping
    public String listRecruitment() {
        return "user/listRecruitment";
    }
}
