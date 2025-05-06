package com.example.bt2;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt5", value = "/bt5")
public class bt5 extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("bt5");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        RequestDispatcher rd = request.getRequestDispatcher("userInfo.jsp");
        rd.forward(request,response);
    }
}