package com.data.ss10.controller.bt2;
import com.data.ss10.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ProductController {

    @GetMapping("/productForm")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "/bt2/productForm";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("product", product);
        return "/bt2/productResult";
    }
}