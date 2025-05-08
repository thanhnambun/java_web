package bt10;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt10", value = "/bt10")
public class bt10 extends HttpServlet {
    private Manager cartManager;

    @Override
    public void init() throws ServletException {
        cartManager = new Manager();
        getServletContext().setAttribute("cartManager", cartManager);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("remove".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            cartManager.removeProduct(id);
        }
        response.sendRedirect("bt10/cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));

            Product product = new Product(id, name, price);
            boolean added = cartManager.addProduct(product);

            if (added) {
                req.setAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công!");
            } else {
                req.setAttribute("message", "Sản phẩm đã tồn tại trong giỏ hàng!");
            }
        }
        req.getRequestDispatcher("bt10/products.jsp").forward(req, resp);
    }
}