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
    public String saveTechnology(@ModelAttribute("technology") TechnologyDTO technologyDTO, Model model) {
        Technology technology;
        String message = null;

        if (technologyDTO.getId() == 0) {
            // Thêm mới
            technology = new Technology();
            technology.setStatus(Status.ACTIVE);
            technology.setName(technologyDTO.getName());
            technologyService.addTechnology(technology);
            message = "Thêm công nghệ mới thành công!";
        } else {
            // Cập nhật
            technology = technologyService.getTechnologyEntityById(technologyDTO.getId());
            if (technology == null) {
                model.addAttribute("error", "Không tìm thấy công nghệ để cập nhật!");
            } else {
                technology.setName(technologyDTO.getName());
                technologyService.updateTechnology(technology);
                message = "Cập nhật công nghệ thành công!";
            }
        }

        // Load lại danh sách để hiển thị cùng trang
        List<TechnologyDTO> technologies = technologyService.getAllTechnologies(1, Integer.MAX_VALUE);
        model.addAttribute("technologies", technologies);
        model.addAttribute("message", message);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", 1); // Không phân trang khi reload
        return "admin/technology"; // quay lại trang danh sách
    }




    @GetMapping("/delete")
    public String deleteTechnology(@RequestParam int id, Model model) {
        technologyService.deleteTechnology(id);
        String message = "Xóa công nghệ thành công!";

        // Load lại danh sách
        List<TechnologyDTO> technologies = technologyService.getAllTechnologies(1, Integer.MAX_VALUE);
        model.addAttribute("technologies", technologies);
        model.addAttribute("message", message);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", 1);
        return "admin/technology"; // không redirect
    }

}
