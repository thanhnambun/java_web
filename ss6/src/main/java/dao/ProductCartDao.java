package dao;

import model.Product;
import model.ProductCart;

import java.util.List;

public interface ProductCartDao {
    List<Product> getAllProducts();
    boolean addProductCart(int productId, int quantity);
    boolean removeProductCart(int productCartId);
    List<ProductCart> getProductCart();
    Product getProductById(int productId);

}
