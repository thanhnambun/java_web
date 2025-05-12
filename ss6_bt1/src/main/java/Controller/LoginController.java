package Controller;import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;



import Model.User;
import Service.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final UsersService svc = new UsersService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u = req.getParameter("username");
        String p = req.getParameter("password");
        try {
            User user = svc.authenticate(u, p);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);
                resp.sendRedirect("books");
            } else {
                req.setAttribute("error", "Sai username hoặc password");
                req.getRequestDispatcher("view/user/login.jsp").forward(req, resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
