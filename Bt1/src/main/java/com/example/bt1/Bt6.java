package com.example.bt1;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Bt6", value = "/bt6")
public class Bt6 extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        register(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        register(request, response);
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fullName = request.getParameter("fullName");
        String className = request.getParameter("className");
        String vehicleType  = request.getParameter("vehicleType");
        String licensePlate = request.getParameter("licensePlate");
        boolean isValid = fullName != null && className != null &&
                vehicleType != null && licensePlate != null &&
                !fullName.isEmpty() && !licensePlate.isEmpty();
        if (isValid) {
            request.setAttribute("status", "success");
            request.setAttribute("message", "Đăng ký vé xe thành công cho " + fullName + "!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("status", "fail");
            request.setAttribute("message", "Đăng ký thất bại. Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("Bt6.jsp").forward(request, response);
        }
    }
}