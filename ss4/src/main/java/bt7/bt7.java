package bt7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt7", value = "/bt7")
public class bt7 extends HttpServlet {
    private List<Product> products;
    @Override
    public void init() throws ServletException {
        products = new ArrayList<>();
        products.add(new Product("P001", "Laptop Dell", 1500.0));
        products.add(new Product("P002", "Smartphone Samsung", 800.0));
        products.add(new Product("P003", "Tai nghe Sony", 200.0));
        products.add(new Product("P004", "Máy ảnh Canon", 1200.0));
        products.add(new Product("P005", "Loa Bluetooth JBL", 150.0));
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Product> filteredProducts = new ArrayList<>(products);

        request.setAttribute("products", filteredProducts);

        request.getRequestDispatcher("bt7.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");

        List<Product> filteredProducts = new ArrayList<>(products);

        if (minPriceStr != null && maxPriceStr != null && !minPriceStr.isEmpty() && !maxPriceStr.isEmpty()) {
            try {
                double minPrice = Double.parseDouble(minPriceStr);
                double maxPrice = Double.parseDouble(maxPriceStr);
                filteredProducts = products.stream()
                        .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("products", filteredProducts);

        request.getRequestDispatcher("bt7.jsp").forward(request, response);
    }

}