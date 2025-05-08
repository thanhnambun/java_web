package bt7;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "bt7", value = "/bt7")
public class bt7 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] names = request.getParameterValues("productName");
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("price");

        List<String> productNames = new ArrayList<>();
        List<Integer> quantityList = new ArrayList<>();
        List<Double> priceList = new ArrayList<>();

        if (names != null && quantities != null && prices != null) {
            productNames = Arrays.asList(names);

            for (int i = 0; i < names.length; i++) {
                try {
                    int qty = Integer.parseInt(quantities[i]);
                    double prc = Double.parseDouble(prices[i]);
                    quantityList.add(qty);
                    priceList.add(prc);
                } catch (Exception e) {
                    quantityList.add(0);
                    priceList.add(0.0);
                }
            }

            double total = OrderProcessor.calculateTotal(productNames, quantityList, priceList);
            request.setAttribute("total", total);
            request.getRequestDispatcher("bt7.jsp").forward(request, response);
        } else {
            response.sendRedirect("bt7.jsp");
        }
    }
}
