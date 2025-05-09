package bt4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt4", value = "/bt4")
public class bt4 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Product> products=new ArrayList<Product>();
        products.add(new Product("Laptop Dell XPS 13", 0, ""));
        products.add(new Product("iPhone 14 Pro", 0, "Điện thoại thông minh với camera 48MP"));
        products.add(new Product("Tai nghe Sony WH-1000XM5", 349.99, "Tai nghe không dây chống ồn cao cấp"));
        products.add(new Product("Máy ảnh Canon EOS R5", 3899.00, "Máy ảnh không gương lật chuyên nghiệp"));

        request.setAttribute("products", products);
        request.getRequestDispatcher("bt4.jsp").forward(request,response);
    }


}