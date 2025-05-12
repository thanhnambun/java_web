package controller;

import model.bt1.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt1", value = "/bt1")
public class bt1 extends HttpServlet {
    private List<Product> productList = new ArrayList<>();

    @Override
    public void init() {
        productList.add(new Product(1, "Áo sơ mi", 150.0, "Áo sơ mi trắng"));
        productList.add(new Product(2, "Quần jeans", 250.0, "Quần jeans xanh"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", productList);
        request.getRequestDispatcher("views/bt1.jsp").forward(request, response);
    }
}