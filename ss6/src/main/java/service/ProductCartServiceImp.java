package service;

import dao.ProductCartDAOImp;
import dao.ProductCartDao;
import model.Product;
import model.ProductCart;

import java.util.List;

public class ProductCartServiceImp implements ProductCartService {
    private final ProductCartDao productCartDao;

    public ProductCartServiceImp() {
        productCartDao= new ProductCartDAOImp();
    }

    @Override
    public List<Product> getAllProducts() {
        return productCartDao.getAllProducts();
    }

    @Override
    public boolean addProductCart(int productId, int quantity) {
        return productCartDao.addProductCart(productId, quantity);
    }

    @Override
    public boolean removeProductCart(int productCartId) {
        return productCartDao.removeProductCart(productCartId);
    }


    @Override
    public List<ProductCart> getProductCart() {
        return productCartDao.getProductCart();
    }

    @Override
    public Product getProductById(int productId) {
        return productCartDao.getProductById(productId);
    }
}
