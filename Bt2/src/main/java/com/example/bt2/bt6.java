package com.example.bt2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt6", value = "/bt6")
public class bt6 extends HttpServlet {
    private int current = 0;

    public void init() {
        if (getServletContext().getAttribute("productList") == null) {
            getServletContext().setAttribute("productList", new ArrayList<Product>());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = (List<Product>) getServletContext().getAttribute("productList");

        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                productList.removeIf(p -> p.getId() == id);
            }
            req.setAttribute("products", productList);
            RequestDispatcher rd = req.getRequestDispatcher("bt6.jsp");
            rd.forward(req, resp);

        } else if ("update".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Product product = productList.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst()
                        .orElse(null);

                if (product != null) {
                    req.setAttribute("product", product);
                    RequestDispatcher rd = req.getRequestDispatcher("update.jsp");
                    rd.forward(req, resp);
                    return;
                }
            }
            req.setAttribute("products", productList);
            RequestDispatcher rd = req.getRequestDispatcher("bt6.jsp");
            rd.forward(req, resp);
        } else {
            req.setAttribute("products", productList);
            RequestDispatcher rd = req.getRequestDispatcher("bt6.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = (List<Product>) getServletContext().getAttribute("productList");

        String nameProduct = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        current++;
        Product product = new Product(current, nameProduct, price);
        productList.add(product);

        request.setAttribute("products", productList);
        RequestDispatcher rd = request.getRequestDispatcher("bt6.jsp");
        rd.forward(request, response);
    }
}