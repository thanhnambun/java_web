package com.data.ss14.controller;


import com.data.ss14.model.B4.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("B4")
@Controller
public class B4Controller {

    @GetMapping("/cart")
    public String showCartForm(Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("product", new Product());
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        List<Product> products = getProductsFromCookies(request);
        model.addAttribute("cart", cart);
        model.addAttribute("products", products);
        return "B4/cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@Valid @ModelAttribute("product") Product product, BindingResult result,
                            HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cart", session.getAttribute("cart"));
            model.addAttribute("products", getProductsFromCookies(request));
            return "B4/cart";
        }
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        cart.add(product);
        List<Product> products = getProductsFromCookies(request);
        products.add(product);
        saveProductsToCookies(products, response);
        return "redirect:/B4/cart";
    }

    @GetMapping("/cart/delete/{index}")
    public String deleteFromCart(@PathVariable("index") int index, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
        List<Product> products = getProductsFromCookies(request);
        if (index >= 0 && index < products.size()) {
            products.remove(index);
            saveProductsToCookies(products, response);
        }
        return "redirect:/B4/cart";
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
            String safeName = product.getName().replaceAll("[|;]", "");
            productString.append(safeName).append("_").append(product.getQuantity());
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