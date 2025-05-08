package bt8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt8", value = "/bt8")
public class bt8 extends HttpServlet {
    @Override
    public void init() {
        if (getServletContext().getAttribute("listBook") == null) {
            getServletContext().setAttribute("listBook", new ArrayList<Book>());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Book> listBook = (List<Book>) getServletContext().getAttribute("listBook");
        String nameBook = request.getParameter("keyword");
        if (nameBook!=null && !nameBook.isEmpty())  {
            List<Book> findBook = listBook.stream().filter(b -> b.getNameBook().equals(nameBook)).collect(Collectors.toList());
            request.setAttribute("listBook", findBook);
        } else {
            request.setAttribute("listBook", listBook);
        }
        request.getRequestDispatcher("bt8.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> listBook = (List<Book>) getServletContext().getAttribute("listBook");
        String nameBook = req.getParameter("title");
        String author = req.getParameter("author");
        int year = Integer.parseInt(req.getParameter("year"));
        listBook.add(new Book(nameBook, author, year));
        getServletContext().setAttribute("listBook", listBook);
        req.setAttribute("listBook", listBook);
        req.getRequestDispatcher("bt8.jsp").forward(req, resp);
    }
}