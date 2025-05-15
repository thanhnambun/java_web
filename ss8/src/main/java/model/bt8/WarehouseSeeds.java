package model.bt8;

public class WarehouseSeeds {
    private int id;
    private int quantity;
    private Seed seed;

    public WarehouseSeeds(int id, int quantity, Seed seed) {
        this.id = id;
        this.quantity = quantity;
        this.seed = seed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }
}