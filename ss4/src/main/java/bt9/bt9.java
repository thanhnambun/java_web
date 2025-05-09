package bt9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet( value = "/bt9")
public class bt9 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Seat> seats = new ArrayList<>();
        String[] rows = {"A", "B", "C", "D", "E"};
        for (String row : rows) {
            for (int i = 1; i <= 10; i++) {
                String name = row + i;
                String status = (name.equals("C5") || name.equals("C6") || name.equals("C7") || name.equals("C8")) ? "booked" : "available";
                double price = row.equals("A") ? 70000 : row.equals("B") ? 80000 : row.equals("C") ? 90000 : 100000;
                seats.add(new Seat(name, status, price));
            }
        }
        request.setAttribute("seats", seats);
        request.getRequestDispatcher("bt9.jsp").forward(request, response);
    }

}