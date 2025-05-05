package com.example.bt1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Bt7", value = "/bt7")
public class Bt7 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<StudentTicket> students = new ArrayList<>();
        students.add(new StudentTicket("Nguyễn Văn A", "10A1", "Xe đạp", "51-A1-12345"));
        students.add(new StudentTicket("Trần Thị B", "11B2", "Xe máy", "52-B2-23456"));
        students.add(new StudentTicket("Lê Văn C", "12C3", "Xe buýt", "Không có"));
        request.setAttribute("studentList", students);
        request.getRequestDispatcher("Bt7.jsp").forward(request, response);

    }

}