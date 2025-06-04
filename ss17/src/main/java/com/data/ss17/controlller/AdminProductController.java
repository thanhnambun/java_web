package com.data.ss17.controlller;

import com.data.ss17.model.Customer;
import com.data.ss17.model.Product;
import com.data.ss17.repository.ProductRepository;
import com.data.ss17.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    @Autowired
    private ProductRepository productService;

    @Autowired
    private CloudinaryService cloudinaryService;

    private boolean checkAdminAccess(HttpSession session) {
        Customer user = (Customer) session.getAttribute("currentUser");
        return user != null && "ADMIN".equals(user.getRole());
    }

    @GetMapping
    public String listProducts(HttpSession session,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "2") int size,
                               @RequestParam(required = false) String search,
                               Model model) {
        if (!checkAdminAccess(session)) return "redirect:/login";

        if (search != null && (search.trim().isEmpty() || "null".equals(search))) {
            search = null;
        }
        List<Product> products = productService.findAllWithPagination(page, size, search);
        long total = productService.countWithSearch(search);
        int totalPages = (int) Math.ceil((double) total / size);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);
        model.addAttribute("content", "admin/products");
        return "admin/admin-layout";
    }

    @GetMapping("/add")
    public String addForm(HttpSession session, Model model) {
        if (!checkAdminAccess(session)) return "redirect:/login";
        model.addAttribute("product", new Product());
        model.addAttribute("content", "admin/product-form");
        return "admin/admin-layout";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product,
                             BindingResult result,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             HttpSession session, Model model) throws IOException {
        if (!checkAdminAccess(session)) return "redirect:/login";

        if (result.hasErrors()) {
            model.addAttribute("content", "admin/product-form");
            return "admin/admin-layout";
        }

        if (!imageFile.isEmpty()) {
            String imageUrl = cloudinaryService.upload(imageFile, "products");
            product.setImageUrl(imageUrl);
        }

        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, HttpSession session, Model model) {
        if (!checkAdminAccess(session)) return "redirect:/login";
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) return "redirect:/admin/products";
        model.addAttribute("product", product);
        model.addAttribute("content", "admin/product-form");
        return "admin/admin-layout";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id,
                              @ModelAttribute("product") @Valid Product product,
                              BindingResult result,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              HttpSession session, Model model) throws IOException {
        if (!checkAdminAccess(session)) return "redirect:/login";

        if (result.hasErrors()) {
            model.addAttribute("content", "admin/product-form");
            return "admin/admin-layout";
        }

        Optional<Product> existingProduct = productService.findById(id);
        if (!imageFile.isEmpty()) {
            String imageUrl = cloudinaryService.upload(imageFile, "products");
            product.setImageUrl(imageUrl);
        } else {
            product.setImageUrl(existingProduct.get().getImageUrl());
        }

        product.setId(id);
        productService.update(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/login";
        productService.delete(id);
        return "redirect:/admin/products";
    }
}