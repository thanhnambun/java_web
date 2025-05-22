package com.data.ss11.controller.bt7;

import com.data.ss11.model.bt7.ProductReview;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductReviewController {
    @GetMapping("/review")
    public String showForm(Model model) {
        model.addAttribute("productReview", new ProductReview());
        return "bt7/review";
    }

    @PostMapping("/review")
    public String submitForm(
            @ModelAttribute("productReview") @Valid ProductReview productReview,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "bt7/review";
        }
        return "bt7/review-success";
    }
}
