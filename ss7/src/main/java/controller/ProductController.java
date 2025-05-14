package controller;

import model.ProductC;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @RequestMapping("/productC/{category}")
    public String getProductsByCategory(
            @PathVariable String category,
            @MatrixVariable(pathVar = "category", value = "color", required = false) String color,
            @MatrixVariable(pathVar = "category", value = "size", required = false) String size,
            Model model) {

        List<ProductC> products = new ArrayList<>();

        products.add(new ProductC("Shirt", "red", "M"));
        products.add(new ProductC("Shirt", "blue", "L"));
        products.add(new ProductC("Shirt", "red", "S"));

        List<ProductC> filtered = products.stream()
                .filter(p -> (color == null || p.getColor().equalsIgnoreCase(color)))
                .filter(p -> (size == null || p.getSize().equalsIgnoreCase(size)))
                .collect(Collectors.toList());

        model.addAttribute("products", filtered);
        model.addAttribute("category", category);
        model.addAttribute("color", color);
        model.addAttribute("size", size);

        return "productC_list";
    }
}