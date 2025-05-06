package com.example.bt2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt1", value = "/bt1")
public class Bt1 extends HttpServlet {


    public void init() {
        System.out.println("Servlet is being initialized (init)");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Servlet Lifecycle</title></head><body>");
        out.println("<h1>Vòng đời của một Servlet</h1>");
        out.println("<ul>");
        out.println("<li><strong>init():</strong> Được gọi một lần duy nhất khi servlet được khởi tạo.</li>");
        out.println("<li><strong>service():</strong> Được gọi mỗi lần có yêu cầu từ client (GET, POST...).</li>");
        out.println("<li><strong>doGet():</strong> Xử lý yêu cầu GET từ trình duyệt.</li>");
        out.println("<li><strong>destroy():</strong> Được gọi một lần duy nhất khi servlet bị loại bỏ khỏi bộ nhớ.</li>");
        out.println("</ul>");
        out.println("</body></html>");
    }

    public void destroy() {
        System.out.println("Servlet is being destroyed (destroy)");
    }
}