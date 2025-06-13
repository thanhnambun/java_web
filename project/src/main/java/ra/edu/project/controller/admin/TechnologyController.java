package ra.edu.project.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.project.dto.TechnologyDTO;
import ra.edu.project.entity.technology.Status;
import ra.edu.project.entity.technology.Technology;
import ra.edu.project.service.TechnologyService;

import java.util.List;

@Controller
@RequestMapping("/admin/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    private static final int PAGE_SIZE = 5;

    @GetMapping
    public String getAllTechnologies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String keyword,
            Model model) {

        List<TechnologyDTO> technologies;
        int totalItems;
        if (keyword != null && !keyword.trim().isEmpty()) {
            technologies = technologyService.searchByName(keyword, page, PAGE_SIZE);
            totalItems = technologyService.searchByName(keyword, 1, Integer.MAX_VALUE).size();
        } else {
            technologies = technologyService.getAllTechnologies(page, PAGE_SIZE);
            totalItems = technologyService.getAllTechnologies(1, Integer.MAX_VALUE).size();
        }

        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        model.addAttribute("technologies", technologies);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);

        return "admin/technology";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            TechnologyDTO technologyDTO = technologyService.getTechnologyById(id);
            model.addAttribute("technology", technologyDTO);
        } else {
            model.addAttribute("technology", new TechnologyDTO());
        }
        return "admin/technology_form";
    }

    @PostMapping("/save")
    public String saveTechnology(@ModelAttribute("technology") TechnologyDTO technologyDTO) {
        Technology technology;

        if (technologyDTO.getId() == 0) {
            // Thêm mới
            technology = new Technology();
            technology.setStatus(Status.ACTIVE); // mặc định ACTIVE khi thêm
        } else {
            // Cập nhật
            technology = technologyService.getTechnologyEntityById(technologyDTO.getId());
            if (technology == null) {
                // Không tìm thấy thì xử lý phù hợp
                return "redirect:/admin/technologies?error=notfound";
            }
        }

        technology.setName(technologyDTO.getName());

        if (technologyDTO.getId() == 0) {
            technologyService.addTechnology(technology);
        } else {
            technologyService.updateTechnology(technology);
        }

        return "redirect:/admin/technologies";
    }


    @GetMapping("/delete")
    public String deleteTechnology(@RequestParam int id) {
        technologyService.deleteTechnology(id);
        return "redirect:/admin/technologies";
    }
}
