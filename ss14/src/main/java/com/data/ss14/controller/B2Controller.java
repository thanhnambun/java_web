package com.data.ss14.controller;

import com.data.ss14.model.B2.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("B2")
@Controller
public class B2Controller {

    @GetMapping("/products")
    public String showProductForm(Model model, HttpServletRequest request) {
        model.addAttribute("product", new Product());
        // Lấy danh sách sản phẩm từ cookie
        List<Product> products = getProductsFromCookies(request);
        model.addAttribute("products", products);
        return "B2/products";
    }

    @PostMapping("/products/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                             HttpServletResponse response, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", getProductsFromCookies(request));
            return "B2/products";
        }
        List<Product> products = getProductsFromCookies(request);
        // Thêm sản phẩm mới
        products.add(product);
        saveProductsToCookies(products, response);
        return "redirect:/B2/products";
    }

    @GetMapping("/products/delete/{index}")
    public String deleteProduct(@PathVariable("index") int index, HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = getProductsFromCookies(request);
        if (index >= 0 && index < products.size()) {
            products.remove(index);
            saveProductsToCookies(products, response);
        }
        return "redirect:/B2/products";
    }

    private List<Product> getProductsFromCookies(HttpServletRequest request) {
        List<Product> products = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("products".equals(cookie.getName())) {
                    String productString = cookie.getValue();
                    if (!productString.isEmpty()) {
                        String[] productEntries = productString.split("\\|");
                        for (String entry : productEntries) {
                            String[] parts = entry.split("_");
                            if (parts.length == 2) {
                                try {
                                    products.add(new Product(parts[0], Integer.parseInt(parts[1])));
                                } catch (NumberFormatException e) {
                                    // Bỏ qua nếu giá không hợp lệ
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        return products;
    }

    private void saveProductsToCookies(List<Product> products, HttpServletResponse response) {
        StringBuilder productString = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            String safeName = product.getName().replaceAll("[|;]", ""); // Loại bỏ | và ;
            productString.append(safeName).append("_").append(product.getPrice());
            if (i < products.size() - 1) {
                productString.append("|");
            }
        }
        Cookie cookie = new Cookie("products", productString.toString());
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}