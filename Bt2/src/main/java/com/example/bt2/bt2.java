package com.example.bt2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt2", value = "/bt2")
public class bt2 extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        PrintWriter out = response.getWriter();
        out.println("name : " + name);
        out.println("age : "+ age);
    }
}