package com.data.ss17.controlller;

import com.data.ss17.model.CartItemView;
import com.data.ss17.model.Customer;
import com.data.ss17.model.Product;
import com.data.ss17.model.ProductCart;
import com.data.ss17.repository.ProductCartRepository;
import com.data.ss17.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCartRepository productCartRepository;

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId,
                            @RequestParam("quantity") Long quantity,
                            HttpSession session) {
        Customer user = (Customer) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        ProductCart existingCart = productCartRepository.findByCustomerAndProduct(user.getId(), productId);
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            productCartRepository.update(existingCart);
        } else {
            ProductCart newCart = new ProductCart();
            newCart.setCustomerId(user.getId());
            newCart.setProductId(productId);
            newCart.setQuantity(quantity);
            productCartRepository.save(newCart);
        }

        return "redirect:/cart/view";
    }

    // Hiển thị giỏ hàng
    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        Customer user = (Customer) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<ProductCart> cartItems = productCartRepository.findByCustomerId(user.getId());
        List<CartItemView> cartViewList = new ArrayList<>();
        double total = 0;

        for (ProductCart cart : cartItems) {
            Optional<Product> productOpt = productRepository.findById(cart.getProductId());
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                cartViewList.add(new CartItemView(cart, product));
                total += product.getProductPrice() * cart.getQuantity();
            }
        }

        model.addAttribute("cartItems", cartViewList);
        model.addAttribute("total", total);
        return "cart";
    }

    // Cập nhật số lượng sản phẩm trong giỏ
    @PostMapping("/update/{cartId}")
    public String updateCartItem(@PathVariable("cartId") Long cartId,
                                 @RequestParam("quantity") Long quantity,
                                 HttpSession session) {
        Customer user = (Customer) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        ProductCart cart = productCartRepository.findById(cartId);
        if (cart != null && cart.getCustomerId().equals(user.getId())) {
            cart.setQuantity(quantity);
            productCartRepository.update(cart);
        }

        return "redirect:/cart/view";
    }

    // Xóa sản phẩm khỏi giỏ
    @GetMapping("/delete/{cartId}")
    public String deleteCartItem(@PathVariable("cartId") Long cartId, HttpSession session) {
        Customer user = (Customer) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        ProductCart cart = productCartRepository.findById(cartId);
        if (cart != null && cart.getCustomerId().equals(user.getId())) {
            productCartRepository.delete(cart);
        }

        return "redirect:/cart/view";
    }
}
