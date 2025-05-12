package Controller;

import Model.Book;
import Service.BookService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class BookController extends HttpServlet {
    private final BookService service = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect("login");
            return;
        }

        String action = req.getParameter("action");
        try {
            switch(action == null ? "" : action) {
                case "add":
                    req.getRequestDispatcher("view/lib/formAdd.jsp").forward(req, resp);
                    break;
                case "edit":
                    String code = req.getParameter("bookCode");
                    Book b = service.getByCode(code);
                    req.setAttribute("book", b);
                    req.getRequestDispatcher("view/lib/formEdit.jsp").forward(req, resp);
                    break;
                default:
                    String kw = req.getParameter("keyword");
                    List<Book> list = (kw == null || kw.isEmpty())
                            ? service.getAll()
                            : service.search(kw);
                    req.setAttribute("books", list);
                    req.getRequestDispatcher("view/lib/listBook.jsp").forward(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            switch(action) {
                case "create":
                    Book nb = new Book(
                            req.getParameter("bookCode"),
                            req.getParameter("title"),
                            req.getParameter("author"),
                            req.getParameter("category"),
                            Integer.parseInt(req.getParameter("quantity"))
                    );
                    service.addBook(nb);
                    resp.sendRedirect("books");
                    break;
                case "update":
                    Book ub = new Book(
                            req.getParameter("bookCode"),
                            req.getParameter("title"),
                            req.getParameter("author"),
                            req.getParameter("category"),
                            Integer.parseInt(req.getParameter("quantity"))
                    );
                    service.updateBook(ub);
                    resp.sendRedirect("books");
                    break;
                case "delete":
                    service.deleteBook(req.getParameter("bookCode"));
                    resp.sendRedirect("books");
                    break;
                default:
                    resp.sendRedirect("books");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}