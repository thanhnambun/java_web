package bt6;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt6", value = "/bt6")
public class bt6 extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Student> students = new ArrayList<>();
        students.add(new Student("SV001", "Nguyen Van A", 20, 8.5));
        students.add(new Student("SV002", "Tran Thi B", 21, 6.5));
        students.add(new Student("SV003", "Le Van C", 19, 7.8));
        students.add(new Student("SV004", "Pham Thi D", 22, 9.0));
        students.add(new Student("SV005", "Hoang Van E", 20, 5.5));

        request.setAttribute("students", students);

        request.getRequestDispatcher("bt6.jsp").forward(request, response);
    }

}