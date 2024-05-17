package com.istiqomah.if2210_tb2_iq9.model.card;

public class Product extends Card {
    private int price;
    private int weight;
    private String name;

    public Product(int id, String name, String imagePath, int price, int weight) {
        super(id, name, imagePath);
        this.price = price;
        this.weight = weight;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "Product";
    }
}