package service;

import model.Product;
import model.ProductCart;

import java.util.List;

public interface ProductCartService {
    List<Product> getAllProducts();
    boolean addProductCart(int productId, int quantity);
    boolean removeProductCart(int productCartId);
    List<ProductCart> getProductCart();
    Product getProductById(int productId);
}
