
package controller.bt2_3_4_5_6_7;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456789";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedIn", true);
            response.sendRedirect("bt2");
        } else {
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            request.getRequestDispatcher("views/bt2/login.jsp").forward(request, response);
        }
    }
}
