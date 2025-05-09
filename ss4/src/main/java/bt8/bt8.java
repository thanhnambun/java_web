package bt8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet( value = "/bt8")
public class bt8 extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Revenue> revenues = new ArrayList<>();
        revenues.add(new Revenue("Tháng 1", 2000.0));
        revenues.add(new Revenue("Tháng 2", 1500.0));
        revenues.add(new Revenue("Tháng 3", 3000.0));
        revenues.add(new Revenue("Tháng 4", 2500.0));
        revenues.add(new Revenue("Tháng 5", 3500.0));
        revenues.add(new Revenue("Tháng 6", 1800.0));

        request.setAttribute("revenues", revenues);
        request.getRequestDispatcher("bt8.jsp").forward(request, response);
    }

}