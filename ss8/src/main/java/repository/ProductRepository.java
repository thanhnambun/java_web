package repository;

import model.Product;
import org.springframework.stereotype.Repository;
import service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public List<Product> findAll() {
        if (productList.isEmpty()) {
            productList.add(new Product(1, "Laptop",20, 1200.0));
            productList.add(new Product(2, "Smartphone",10, 800.0));
            productList.add(new Product(3, "Headphones",4, 150.0));
        }
        return productList;
    }

    public boolean addProduct(Product product) {
        return productList.add(product);
    }
}
