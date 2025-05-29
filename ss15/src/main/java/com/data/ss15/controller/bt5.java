package com.data.ss15.controller;

import com.data.ss15.model.Cart;
import com.data.ss15.model.Product;
import com.data.ss15.model.Review;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class bt5 {
    private static final List<Product> products = new ArrayList<>();
    private static final List<Review> reviews = new ArrayList<>();
    private static final List<Cart> carts = new ArrayList<>();


    static {
        products.add(new Product("P01", "iPhone 15", 999));
        products.add(new Product("P02", "Samsung Galaxy S23", 899));
        products.add(new Product("P03", "Xiaomi 13", 499));
    }

    @GetMapping("listProduct")
    public String listProduct(Model model) {
        model.addAttribute("products", products);
        return "bt5";
    }

    @PostMapping("search")
    public String search(@RequestParam("nameSearch") String nameSearch, Model model) {
        if (nameSearch == null || nameSearch.trim().isEmpty()) {
            model.addAttribute("products", products);
            model.addAttribute("message", "Vui lòng nhập tên sản phẩm cần tìm");
            return "bt5";
        }

        List<Product> matchedProducts = products.stream()
                .filter(p -> p.getName().toLowerCase().contains(nameSearch.trim().toLowerCase()))
                .collect(Collectors.toList());

        if (matchedProducts.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy sản phẩm phù hợp.");
        }

        model.addAttribute("products", matchedProducts); // chỉ còn 1 biến products duy nhất
        return "bt5";
    }
    @GetMapping("/listProduct/{id}")
    public String detail(@PathVariable String id, Model model) {
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (product == null) {
            model.addAttribute("message", "Không tìm thấy sản phẩm.");
            return "error";
        }

        // Lọc review theo id sản phẩm
        List<Review> productReviews = reviews.stream()
                .filter(r -> r.getIdProduct().equals(id))
                .collect(Collectors.toList());

        model.addAttribute("product", product);
        model.addAttribute("reviews", productReviews); // Thêm dòng này
        model.addAttribute("newReview", new Review()); // Để form hoạt động đúng

        return "bt6/product_detail";
    }

    @PostMapping("/listProduct/{id}/review")
    public String addReview(@PathVariable("id") String productId,
                            @ModelAttribute("newReview") Review review) {
        review.setId(UUID.randomUUID().toString());
        review.setIdProduct(productId);
        reviews.add(review);
        return "redirect:/listProduct/" + productId;
    }
    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productId") String productId,
                            @RequestParam(value = "quantity", defaultValue = "1") int quantity) {

        for (Cart cart : carts) {
            if (cart.getIdProduct().equals(productId)) {
                cart.setQuantity(cart.getQuantity() + quantity);
                return "redirect:/cart";
            }
        }
        Product product = products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
        if (product != null) {
            double subtotal = product.getPrice() * quantity;
            carts.add(new Cart(UUID.randomUUID().toString(), product.getId(), quantity, subtotal));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<Cart> cartViews = new ArrayList<>();
        double total = 0;

        for (Cart cart : carts) {
            Product product = products.stream()
                    .filter(p -> p.getId().equals(cart.getIdProduct()))
                    .findFirst()
                    .orElse(null);
            if (product != null) {
                double subtotal = product.getPrice() * cart.getQuantity();
                total += subtotal;
                cartViews.add(new Cart(UUID.randomUUID().toString(), product.getId(), cart.getQuantity(), subtotal));
            }
        }

        model.addAttribute("cartItems", cartViews);
        model.addAttribute("total", total);
        return "cart_view";
    }

}
