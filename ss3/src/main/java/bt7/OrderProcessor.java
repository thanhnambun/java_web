package bt7;

import java.util.List;

public class OrderProcessor {
    public static double calculateTotal(List<String> products, List<Integer> quantities, List<Double> prices) {
        double total = 0.0;
        for (int i = 0; i < products.size(); i++) {
            total += quantities.get(i) * prices.get(i);
        }
        return total;
    }
}