package controller.b9;

import model.b9.Category;
import model.b9.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller("productControllerB9")
@RequestMapping("b9/products")
public class ProductController {

    private final List<Category> categoryList = Arrays.asList(
            new Category(1, "Electronics"),
            new Category(2, "Clothing"),
            new Category(3, "Books")
    );

    private final List<Product> productList = new ArrayList<>();
    private int productIdSequence = 1;

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryList;
    }

    @GetMapping
    public String listProducts(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Product> filtered = Optional.ofNullable(keyword)
                .filter(k -> !k.trim().isEmpty())
                .map(k -> productList.stream()
                        .filter(p -> p.getName().toLowerCase().contains(k.toLowerCase()))
                        .collect(Collectors.toList()))
                .orElse(productList);

        model.addAttribute("products", filtered);
        return "b9/productList";
    }


    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable int id, Model model) {
        Product product = findById(id);
        if (product == null) return "redirect:/b9/products";
        model.addAttribute("product", product);
        return "b9/productDetails";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "b9/addProduct";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        product.setId(productIdSequence++);
        productList.add(product);
        redirectAttributes.addFlashAttribute("message", "Sản phẩm đã được thêm thành công!");
        return "redirect:/b9/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        Product product = findById(id);
        if (product == null) return "redirect:/products";
        model.addAttribute("product", product);
        return "b9/editProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product updatedProduct, RedirectAttributes redirectAttributes) {
        Product product = findById(updatedProduct.getId());
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setCategoryId(updatedProduct.getCategoryId());
            redirectAttributes.addFlashAttribute("message", "Cập nhật sản phẩm thành công!");
        }
        return "redirect:/b9/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id, RedirectAttributes redirectAttributes) {
        productList.removeIf(p -> p.getId() == id);
        redirectAttributes.addFlashAttribute("message", "Đã xóa sản phẩm thành công!");
        return "redirect:/b9/products";
    }

    private Product findById(int id) {
        return productList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
