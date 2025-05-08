import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt2", value = "/bt2")
public class bt2 extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String  name = request.getParameter("name");
        String email = request.getParameter("email");
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.getRequestDispatcher("user.jsp").forward(request,response);
    }

}