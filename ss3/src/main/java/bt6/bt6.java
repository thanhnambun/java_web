package bt6;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt6", value = "/bt6")
public class bt6 extends HttpServlet {
    public void init() {
        if (getServletContext().getAttribute("productList") == null) {
            getServletContext().setAttribute("productList", new ArrayList<Product>());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> productList = (List<Product>) getServletContext().getAttribute("productList");
        request.setAttribute("products", productList);
        RequestDispatcher rd = request.getRequestDispatcher("bt6.jsp");
        rd.forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> productList = (List<Product>) getServletContext().getAttribute("productList");
        int id = Integer.parseInt(request.getParameter("id"));
        String nameProduct = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        productList.add(new Product(id,nameProduct,price,quantity,description,true));

        getServletContext().setAttribute("productList", productList);
        request.getRequestDispatcher("bt6.jsp").forward(request, response);
    }

    public void destroy() {
    }
}