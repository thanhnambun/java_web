package controller;

import model.Product;
import model.ProductCart;
import service.ProductCartService;
import service.ProductCartServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "cart", value = "/cart")
public class ProductCartController  extends HttpServlet {
    private final ProductCartService productCartService;

    public ProductCartController () {
        productCartService = new ProductCartServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductCart> cartList = productCartService.getProductCart();
        double totalAmount = 0;

        for (ProductCart item : cartList) {
            Product product = productCartService.getProductById(item.getProductId());
            if (product != null) {
                totalAmount += product.getPrice() * item.getQuantity();
            }
        }

        req.setAttribute("cartList", cartList);
        req.setAttribute("totalAmount", totalAmount);
        req.getRequestDispatcher("bt3/cart.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        productCartService.removeProductCart(productId);
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
