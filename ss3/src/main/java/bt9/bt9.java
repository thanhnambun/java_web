package bt9;import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt9", value = "/bt9")
public class bt9 extends HttpServlet {
    private int userId = 1;
    private UserManager userManager;

    @Override
    public void init() throws ServletException {
        userManager = new UserManager();
        getServletContext().setAttribute("userManager", userManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userManager.removeUser(id);
        }

        response.sendRedirect("bt9/useList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String username = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(userId++, username, email);
        userManager.addUser(user);
        req.setAttribute("message", "Đăng ký thành công!");
        resp.sendRedirect("bt9/useList.jsp");
    }
}