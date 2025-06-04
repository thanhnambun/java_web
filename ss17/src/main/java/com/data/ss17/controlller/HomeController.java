package com.data.ss17.controlller;

import com.data.ss17.model.Product;
import com.data.ss17.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    private final ProductRepository productRepository;
    @Autowired
    public  HomeController(ProductRepository  proRepo) {
        this.productRepository = proRepo;
    }
    @GetMapping("/home")
    public String viewHomePage(Model model) {
        List<Product> productPage = productRepository.findAll();
        model.addAttribute("productPage", productPage);
        return "home";
    }
    @GetMapping("/product/detail/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return "redirect:/home";
        }
        model.addAttribute("product", product.get());
        return "product_detail";
    }
}