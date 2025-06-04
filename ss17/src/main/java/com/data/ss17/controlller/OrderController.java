package com.data.ss17.controlller;

import com.data.ss17.model.Customer;
import com.data.ss17.model.Orders;
import com.data.ss17.model.ProductCart;
import com.data.ss17.repository.OrderRepository;
import com.data.ss17.repository.ProductCartRepository;
import com.data.ss17.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCartRepository productCartRepository;

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Customer user = (Customer) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
        List<ProductCart> cartItems = productCartRepository.findByCustomerId(user.getId());
        double total = cartItems.stream()
                .mapToDouble(cart -> productRepository.findById(cart.getProductId()).get().getProductPrice() * cart.getQuantity())
                .sum();
        model.addAttribute("order", new Orders());
        model.addAttribute("total", total);
        return "checkout";
    }

    @PostMapping("/submit")
    public String submitOrder(@ModelAttribute("order") Orders order, HttpSession session, Model model) {
        Customer user = (Customer) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
        List<ProductCart> cartItems = productCartRepository.findByCustomerId(user.getId());
        if (cartItems.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống!");
            return "order-success";
        }
        String listProduct = cartItems.stream()
                .map(c -> c.getProductId() + ":" + c.getQuantity())
                .collect(Collectors.joining(","));
        double total = cartItems.stream()
                .mapToDouble(cart -> productRepository.findById(cart.getProductId()).get().getProductPrice() * cart.getQuantity())
                .sum();

        order.setCustomerId(user.getId());
        order.setListProduct(listProduct);
        order.setTotalMoney(BigDecimal.valueOf(total));
        order.setStatus("PENDING");
        orderRepository.save(order);

        productCartRepository.clearCart(user.getId());

        model.addAttribute("message", "Đặt hàng thành công! Cảm ơn bạn đã mua sắm.");
        return "order-success";
    }
}