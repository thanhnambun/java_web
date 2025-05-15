package model.bt8;

public class Seed {
    private int id;
    private String seedsName;
    private double price;
    private String imageUrl;

    public Seed(int id, String seedsName, double price, String imageUrl) {
        this.id = id;
        this.seedsName = seedsName;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeedsName() {
        return seedsName;
    }

    public void setSeedsName(String seedsName) {
        this.seedsName = seedsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}