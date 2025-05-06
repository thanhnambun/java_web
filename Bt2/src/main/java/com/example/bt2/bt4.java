package com.example.bt2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt4", value = "/bt4")
public class bt4 extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("email", email);

        response.sendRedirect("thankyou.jsp");
    }

}