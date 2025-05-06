package com.example.bt2;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt3", value = "/bt3")
public class bt3 extends HttpServlet {



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        RequestDispatcher rd = request.getRequestDispatcher("/display.jsp");
        rd.forward(request,response);
    }

}