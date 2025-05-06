package com.example.bt2;

public class Product {
    private int idSequen=0;
    private int id;
    private String nameProduct;
    private double price;

    public Product(int id, String nameProduct, double price) {
        this.id = ++idSequen;
        this.nameProduct = nameProduct;
        this.price = price;
    }

    public int getId() {
        return id;
    }


    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
