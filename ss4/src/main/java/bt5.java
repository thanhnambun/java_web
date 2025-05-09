import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import bt4.Product;

@WebServlet(name = "bt5", value = "/bt5")
public class bt5 extends HttpServlet {
    private List<Product> products;

    @Override
    public void init() throws ServletException {
        products = new ArrayList<>();
        products.add(new Product("Laptop Dell XPS 13", 1200.00, "Laptop cao cấp với màn hình 13 inch"));
        products.add(new Product("iPhone 14 Pro", 999.00, "Điện thoại thông minh với camera 48MP"));
        products.add(new Product("Tai nghe Sony WH-1000XM5", 349.99, "Tai nghe không dây chống ồn cao cấp"));
        products.add(new Product("Máy ảnh Canon EOS R5", 3899.00, "Máy ảnh không gương lật chuyên nghiệp"));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        Product foundProduct = null;
        int searchId = -1;

        if (idParam != null && !idParam.isEmpty()) {
            try {
                searchId = Integer.parseInt(idParam);
                if (searchId >= 0 && searchId < products.size()) {
                    foundProduct = products.get(searchId);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("products", products);
        request.setAttribute("foundProduct", foundProduct);
        request.setAttribute("searchId", searchId);

        request.getRequestDispatcher("bt5.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}