import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt2", value = "/bt2")
public class bt2 extends HttpServlet {
    private final String VALID_USERNAME = "admin";
    private final String VALID_PASSWORD = "123";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            req.setAttribute("message", "Đăng nhập thành công. Chào mừng " + username + "!");
        } else {
            req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
        }

        req.getRequestDispatcher("bt2.jsp").forward(req, resp);
    }
}