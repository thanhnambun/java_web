package bt10;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<Product> cart;

    public Manager() {
        this.cart = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        for (Product p : cart) {
            if (p.getId() == product.getId()) {
                return false;
            }
        }
        cart.add(product);
        return true;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void removeProduct(int id) {
        cart.removeIf(product -> product.getId() == id);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        return total;
    }
}
