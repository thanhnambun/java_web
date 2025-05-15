package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ProductService;

import java.util.List;

@Controller
public class ProductController {
    private int productIdSequence = 1;
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProductList(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productList";
    }
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }
    @PostMapping("/save")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        product.setId(productIdSequence++);

        if (productService.addProduct(product)){
            redirectAttributes.addFlashAttribute("message", "Sản phẩm đã được thêm thành công!");
        }else {
            redirectAttributes.addFlashAttribute("message", "Sản phẩm không được thêm thành công!");
        }
        return "redirect:/addProduct";
    }
}
