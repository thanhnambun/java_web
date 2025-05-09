package bt1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt1", value = "/bt1")
public class bt1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Laptop", 1200.0, "Laptop hiệu năng cao"));
        productList.add(new Product(2, "Smartphone", 800.0, "Điện thoại thông minh"));
        productList.add(new Product(3, "Headphones", 150.0, "Tai nghe chống ồn"));

        req.setAttribute("products", productList);
        req.getRequestDispatcher("bt1.jsp").forward(req, resp);
    }

}