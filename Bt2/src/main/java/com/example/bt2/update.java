package com.example.bt2;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "update", value = "/update")
public class update extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> productList = (List<Product>) getServletContext().getAttribute("productList");
        String nameProduct = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int id = Integer.parseInt(request.getParameter("id"));
        for (Product p : productList) {
            if (p.getId() == id) {
                p.setNameProduct(nameProduct);
                p.setPrice(price);
                break;
            }
        }
        request.setAttribute("products", productList);
        RequestDispatcher rd = request.getRequestDispatcher("bt6.jsp");
        rd.forward(request, response);
    }

}