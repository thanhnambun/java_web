package bt9;

public class Seat {
    private String name;
    private String status;
    private double price;

    public Seat(String name, String status, double price) {
        this.name = name;
        this.status = status;
        this.price = price;
    }

    public String getName() { return name; }
    public String getStatus() { return status; }
    public double getPrice() { return price; }
}

